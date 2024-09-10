/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionario;

import java.util.Scanner;

/**
 *
 * @author suelen
 */
public class SistemaInterno {

    Scanner teclado = new Scanner(System.in);

    public void login(Autenticavel fun) {

        System.out.println("*** LOGIN ***");
        System.out.println("Informe sua senha: ");
        int senha = teclado.nextInt();

        boolean resul = fun.autentica(senha);
        
        if (resul) {
            System.out.println("Acesso Permitido!");
        } else {
            System.out.println("Acesso Negado!");
        }
    }
}
