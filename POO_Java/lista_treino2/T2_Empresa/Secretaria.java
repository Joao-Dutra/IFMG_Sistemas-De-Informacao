/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionario;

/**
 *
 * @author suelen
 */
public class Secretaria extends Funcionario{
    
    public Secretaria(String nome, String cpf) {
        super(nome, cpf);
    }
    
    
     public double calculaBonificacao(){
        
        return (this.salario*1.25);
        
    }
    
}
