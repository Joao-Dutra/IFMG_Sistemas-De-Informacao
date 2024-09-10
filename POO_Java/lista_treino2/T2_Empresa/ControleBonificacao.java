/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionario;

/**
 *
 * @author suelen
 */
public class ControleBonificacao {
    
    private double totalDeBonif = 0;
    
    
    public void regitra(Funcionario f){
        
        this.totalDeBonif+=f.calculaBonificacao();
    }
    
    public double getTotalDeBonif(){
        
        return this.totalDeBonif;
    }
    
}
