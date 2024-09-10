.data
array:      .space 32               
tamanho_array: .word 8              
contador_trocas: .word 0            
num_informado: .word 0              

.text
.globl main

main:
    # Exibe a mensagem inicial
    li   $v0, 4                     
    la   $a0, mensagem_inicial      
    syscall

    # Carrega o endereço do array e o tamanho do array
    la   $t0, array                 
    lw   $t1, tamanho_array         

    # Solicita ao usuário que insira os elementos do array
    addi $t2, $zero, 0              

entrada_array:
    bge  $t2, $t1, bubble_sort      

    # Exibe mensagem para inserir um número
    li   $v0, 4                     
    la   $a0, mensagem_prompt       
    syscall

    # Lê o valor do usuário
    li   $v0, 5                     
    syscall

    # Verifica se o valor é 0
    beq  $v0, $zero, bubble_sort    

    # Armazena o valor no array
    sw   $v0, 0($t0)

    # Incrementa o contador de números informados
    lw   $t4, num_informado
    addi $t4, $t4, 1
    sw   $t4, num_informado

    # Incrementa o ponteiro do array e i
    addi $t0, $t0, 4                
    addi $t2, $t2, 1                
    j    entrada_array              

bubble_sort:
    # Inicia o Bubble Sort
    la   $t0, array                 
    lw   $t1, num_informado         
    addi $t2, $zero, 0              

    # Inicializa o contador de trocas
    la   $t3, contador_trocas       
    li   $t4, 0                     
    sw   $t4, 0($t3)                

loop_externo:
    sub  $t5, $t1, $t2
    blez $t5, fim_ordenacao

    addi $t6, $zero, 0              

loop_interno:
    sub  $t7, $t1, $t2
    addi $t7, $t7, -1
    bge  $t6, $t7, proximo_externo

    sll  $t8, $t6, 2
    add  $t9, $t0, $t8
    lw   $s0, 0($t9)
    lw   $s1, 4($t9)

    ble  $s0, $s1, pular_troca

    # Troca os valores
    sw   $s1, 0($t9)
    sw   $s0, 4($t9)

    # Incrementa o contador de trocas
    lw   $t4, 0($t3)                
    addi $t4, $t4, 1                
    sw   $t4, 0($t3)                

pular_troca:
    addi $t6, $t6, 1
    j    loop_interno

proximo_externo:
    addi $t2, $t2, 1
    j    loop_externo

fim_ordenacao:
    # Exibe o array ordenado
    la   $t0, array                 
    addi $t2, $zero, 0              

imprimir_array:
    bge  $t2, $t1, imprimir_contador_trocas

    sll  $t8, $t2, 2
    add  $t9, $t0, $t8
    lw   $s0, 0($t9)

    li   $v0, 1
    move $a0, $s0
    syscall

    li   $v0, 11
    li   $a0, 32
    syscall

    addi $t2, $t2, 1
    j    imprimir_array

imprimir_contador_trocas:
    # Exibe o contador de trocas
    li   $v0, 4                    
    la   $a0, mensagem_trocas       
    syscall

    lw   $a0, 0($t3)                
    li   $v0, 1                     
    syscall

fim_programa:
    li   $v0, 10
    syscall

.data
mensagem_prompt: .asciiz "Insira um número do vagão: "
mensagem_trocas: .asciiz "\nNúmero de trocas de vagões: "
mensagem_inicial: .asciiz "O limite de vagões são '8'.\nInforme '0' caso já tenha informado todas os vagões.\n "