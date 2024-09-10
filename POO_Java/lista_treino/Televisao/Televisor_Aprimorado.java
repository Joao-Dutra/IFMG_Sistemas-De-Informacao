package poo;

public class Televisor_Aprimorado {
     
    // atributos
    int canal;
    int volume;        
    boolean ligado;
    boolean status;
    String statusLigDes;
    // metodos
    
    void statusTelevisor(){
        if (this.status == false) {
            this.canal = 1;
            this.volume = 0;           
            System.out.println("O Televisor está desligado! É necessário liga-lo para utiliza-lo, deseja liga-lo?");
        }else{
            System.out.println("O Televisor está ligado! Informe um comando : ");
        }
    }
    
    void aumentarVolume(){
        if (this.volume < 10) {
           this.volume += 1; 
            System.out.println("Volume aumentado para : " + this.volume);
        }else{
            System.out.println("Volume já está no máximo : " + this.volume);
        }
    }
    
    void reduzirVolume(){
        if(this.volume > 0){
          this.volume -= 1;
            System.out.println("Volume reduzido para : " + this.volume);
        }else{
            System.out.println("Volume já está no mínimo : " + this.volume);
        }
    }
    
    void subirCanal(){
        if(this.canal < 16){
        this.canal += 1;
            System.out.println("Canal ajustado para : " + this.canal);
        }else{
            System.out.println("Não tem mais como subir de canal!");
        }
    }
    
    void descerCanal(){
        if(this.canal > 1) {
        this.canal -= 1;
            System.out.println("Canal ajustado para : " + this.canal);
        }else{
            System.out.println("Não tem mais como descer de canal!");
        }
    }
    
    void ligarTelevisor(){
        if (this.ligado == false) {
            this.ligado = true;               
            this.status = true;
            statusLigDes = "ligado";
            System.out.println("Televisor Ligado!");
        }
        
    }
    
    void desligarTelevisor(){
        if (this.ligado == true) {
            this.ligado = false;               
            this.status = false;
            statusLigDes = "desligado";            
            this.canal = 1;
            this.volume = 0; 
            System.out.println("Televisor Desligado!");
        }
    }    
    
    void mostraStatus(){
        System.out.println("Está no canal : " + this.canal + " o volume está em : " + this.volume + " e o televisor está " + statusLigDes);
    }
}
