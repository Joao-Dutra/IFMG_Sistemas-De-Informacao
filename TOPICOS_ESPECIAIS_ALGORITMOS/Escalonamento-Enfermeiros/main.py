import csv
import random
import numpy as np
import matplotlib.pyplot as plt

NUM_PROFISSIONAIS = 152
NUM_DIAS = 30
TURNOS = [0, 1, 2]  # 0=Manhã, 1=Tarde, 2=Noite
SETORES = [0, 1, 2, 3, 4, 5]

PESO_DEMANDA_FALTA = 10
PESO_DEMANDA_EXCESSO = 5
PESO_PREFERENCIA = 5
PESO_NOITE_MANHA = 300

# Lê profissionais
def carregar_profissionais():
    profissionais = {}
    with open('profissionais.csv', newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            profissionais[int(row['id'])] = int(row['cargo'])  # 0=Enfermeiro, 1=Técnico
    return profissionais

# Lê indisponibilidades
def carregar_indisponibilidades():
    indisponibilidades = {p: [] for p in range(NUM_PROFISSIONAIS)}
    with open('indisponibilidades.csv', newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            p = int(row['id_profissional'])
            d = int(row['id_dia'])
            indisponibilidades[p].append(d)
    return indisponibilidades

# Lê preferências
def carregar_preferencias():
    preferencias = {p: [] for p in range(NUM_PROFISSIONAIS)}
    with open('preferencias.csv', newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            p = int(row['id_profissional'])
            setor = row['id_setor']
            turno = row['id_turno']
            preferencias[p].append((turno, setor))
    return preferencias

# Lê demandas
def carregar_demandas():
    demandas = {}
    with open('demanda.csv', newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            setor = int(row['id_setor'])
            turno = int(row['id_turno'])
            chave = (setor, turno)
            demandas[chave] = {
                0: (int(row['min_enf']), int(row['max_enf'])),   # Enfermeiros
                1: (int(row['min_tec']), int(row['max_tec']))    # Técnicos
            }
    return demandas


def inicializa_escala(indisponibilidades, demandas, profissionais, preferencias):
    escala = [[None for _ in range(NUM_DIAS)] for _ in range(NUM_PROFISSIONAIS)]

    for d in range(NUM_DIAS):
        for (setor, turno), necessidade in demandas.items():
            for tipo in [0, 1]:
                min_enf, max_enf = necessidade[tipo]

                # Candidatos válidos
                candidatos = [p for p in range(NUM_PROFISSIONAIS)
                              if profissionais[p] == tipo
                              and d not in indisponibilidades[p]
                              and escala[p][d] is None]

                # Ordenar candidatos: preferem esse turno/setor > aleatório
                candidatos_pref = [p for p in candidatos if (setor, turno) in preferencias[p]]
                candidatos_nao_pref = [p for p in candidatos if (setor, turno) not in preferencias[p]]

                selecionados = []

                # Tenta preencher o mínimo com preferências
                if len(candidatos_pref) >= min_enf:
                    selecionados.extend(candidatos_pref[:min_enf])
                else:
                    # complementa com os demais
                    selecionados.extend(candidatos_pref)
                    falta = min_enf - len(candidatos_pref)
                    selecionados.extend(candidatos_nao_pref[:falta])

                # Preenche na escala
                for p in selecionados:
                    escala[p][d] = (turno, setor)

                # Preenche até o máximo, se ainda houver candidatos disponíveis
                restantes = [p for p in candidatos if p not in selecionados]
                max_extra = max_enf - len(selecionados)
                if max_extra > 0 and restantes: # Garante que há vagas e candidatos
                    selecionados_extra = random.sample(restantes, min(len(restantes), max_extra))
                    for p in selecionados_extra:
                        escala[p][d] = (turno, setor)

    for p in range(NUM_PROFISSIONAIS):
        for d in range(NUM_DIAS):
            if escala[p][d] is None and d not in indisponibilidades[p]:
                # Tenta usar preferências primeiro
                prefs = list(preferencias[p])
                random.shuffle(prefs)
                if prefs:
                    turno, setor = prefs[0]
                else:
                    turno = random.choice(TURNOS)
                    setor = random.choice(SETORES)
                escala[p][d] = (turno, setor)

    return escala

def funcao_objetivo(escala, demandas, preferencias, profissionais):
    F1 = 0  # Atende demanda
    F2 = 0  # Preferencias
    F3 = 0  # Penalidade noite->manha

    for dia in range(NUM_DIAS):
        for turno in TURNOS:
            for setor in SETORES:
                contagem = {0: 0, 1: 0}
                for p in range(NUM_PROFISSIONAIS):
                    if escala[p][dia] == (turno, setor):
                        tipo = profissionais[p]
                        contagem[tipo] += 1

                chave = (setor, turno)
                if chave in demandas:
                    for tipo in [0, 1]:
                        min_, max_ = demandas[chave][tipo]
                        if contagem[tipo] < min_:
                            F1 -= (min_ - contagem[tipo]) * PESO_DEMANDA_FALTA
                        elif contagem[tipo] > max_:
                            F1 -= (contagem[tipo] - max_) * PESO_DEMANDA_EXCESSO

    for p in range(NUM_PROFISSIONAIS):
        for d in range(NUM_DIAS):
            if escala[p][d] in preferencias[p]:
                F2 += PESO_PREFERENCIA

            if d > 0 and escala[p][d - 1] and escala[p][d]:
                turno_ant = escala[p][d - 1][0]
                turno_atual = escala[p][d][0]
                if turno_ant == 2 and turno_atual == 0:
                    F3 -= PESO_NOITE_MANHA

    return F1 + F2 + F3

def busca_tabu(escala_inicial, demandas, preferencias, profissionais, max_iter=5000, tabu_tam=50):
    escala_atual = [linha[:] for linha in escala_inicial]
    melhor_escala = [linha[:] for linha in escala_inicial]
    melhor_fo = funcao_objetivo(escala_atual, demandas, preferencias, profissionais)

    tabu = []
    historico_fo = []

    for iter in range(max_iter):
        vizinhos = gerar_vizinhos(escala_atual, indisponibilidades)
        melhor_vizinho = None
        melhor_fo_viz = float('-inf')

        for vizinho in vizinhos:
            movimento = hash(str(vizinho))
            if movimento in tabu:
                continue
            fo = funcao_objetivo(vizinho, demandas, preferencias, profissionais)
            if fo > melhor_fo_viz:
                melhor_fo_viz = fo
                melhor_vizinho = vizinho

        if melhor_vizinho is not None:
            escala_atual = melhor_vizinho
            tabu.append(hash(str(melhor_vizinho)))
            if len(tabu) > tabu_tam:
                tabu.pop(0)
            if melhor_fo_viz > melhor_fo:
                melhor_escala = melhor_vizinho
                melhor_fo = melhor_fo_viz

        historico_fo.append(melhor_fo)
        print(f"Iteração {iter + 1}: FO = {melhor_fo}")

    return melhor_escala, historico_fo

def gerar_vizinhos(escala, indisponibilidades):
    vizinhos = []

    for _ in range(150):
        nova_escala = [linha[:] for linha in escala]

        tipo = random.choice(['swap_turno', 'swap_dia', 'modifica'])

        if tipo == 'swap_turno':
            p1, p2 = random.sample(range(NUM_PROFISSIONAIS), 2)
            d = random.randint(0, NUM_DIAS - 1)
            if d in indisponibilidades[p1] or d in indisponibilidades[p2]:
                continue  # pula vizinho inválido
            nova_escala[p1][d], nova_escala[p2][d] = nova_escala[p2][d], nova_escala[p1][d]

        elif tipo == 'swap_dia':
            p = random.randint(0, NUM_PROFISSIONAIS - 1)
            d1, d2 = random.sample(range(NUM_DIAS), 2)
            if d1 in indisponibilidades[p] or d2 in indisponibilidades[p]:
                continue
            nova_escala[p][d1], nova_escala[p][d2] = nova_escala[p][d2], nova_escala[p][d1]

        elif tipo == 'modifica':
            p = random.randint(0, NUM_PROFISSIONAIS - 1)
            d = random.randint(0, NUM_DIAS - 1)
            if d in indisponibilidades[p]:
                continue
            atual = nova_escala[p][d]
            if atual is not None:
                turno, setor = atual
                if random.random() < 0.5:
                    novo_turno = random.choice([t for t in TURNOS if t != turno])
                    nova_escala[p][d] = (novo_turno, setor)
                else:
                    novo_setor = random.choice([s for s in SETORES if s != setor])
                    nova_escala[p][d] = (turno, novo_setor)

        vizinhos.append(nova_escala)

    return vizinhos

# -------- EXECUÇÃO PRINCIPAL --------
profissionais = carregar_profissionais()
indisponibilidades = carregar_indisponibilidades()
preferencias = carregar_preferencias()
demandas = carregar_demandas()

escala_inicial = inicializa_escala(indisponibilidades, demandas, profissionais, preferencias)
melhor_escala, historico_fo = busca_tabu(escala_inicial, demandas, preferencias, profissionais)

plt.plot(historico_fo)
plt.xlabel('Iteração')
plt.ylabel('Função Objetivo')
plt.title('Evolução da FO na Busca Tabu')
plt.grid(True)
plt.tight_layout()
plt.show()

with open('escala_resultado.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(['profissional', 'dia', 'turno', 'setor'])
    for p in range(NUM_PROFISSIONAIS):
        for d in range(NUM_DIAS):
            if melhor_escala[p][d]:
                turno, setor = melhor_escala[p][d]
                writer.writerow([p, d, turno, setor])
