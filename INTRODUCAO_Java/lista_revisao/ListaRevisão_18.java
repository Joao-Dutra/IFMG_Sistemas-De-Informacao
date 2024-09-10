package listarevisao;

import java.util.Scanner;

public class ListaRevisão_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Definindo o número de dias e horas na semana
        int diasSemana = 7;
        int horasDia = 24;

        // Inicializando o array bidimensional para armazenar as temperaturas
        double[][] temperaturas = new double[diasSemana][horasDia];

        // Leitura das temperaturas
        for (int dia = 0; dia < diasSemana; dia++) {
            System.out.println("Dia " + (dia + 1) + ":");
            for (int hora = 0; hora < horasDia; hora++) {
                System.out.print("Digite a temperatura para a hora " + (hora + 1) + ": ");
                temperaturas[dia][hora] = scanner.nextDouble();
            }
        }

        // Consultas das maiores temperaturas por hora
        int horaConsulta;
        do {
            System.out.print("Digite a hora que deseja consultar (ou um valor negativo para sair): ");
            horaConsulta = scanner.nextInt();

            if (horaConsulta >= 1 && horaConsulta <= horasDia) {
                double maiorTemperatura = temperaturas[0][horaConsulta - 1];
                for (int dia = 1; dia < diasSemana; dia++) {
                    if (temperaturas[dia][horaConsulta - 1] > maiorTemperatura) {
                        maiorTemperatura = temperaturas[dia][horaConsulta - 1];
                    }
                }
                System.out.println("Maior temperatura na hora " + horaConsulta + ": " + maiorTemperatura);
            } else if (horaConsulta >= 1) {
                System.out.println("Hora inválida. A hora deve estar entre 1 e " + horasDia);
            }
        } while (horaConsulta >= 1);

        System.out.println("Consulta encerrada. Obrigado!");
        scanner.close();
    }
}