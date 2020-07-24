package BackEnd;

import java.io.Serializable;

public class Cama implements Serializable {
    
    //Variaveis de instancia
    private String nCama;
    private String doente;
    private String estado = "Disponivel";
     
    //Construtores
    public Cama() {}
    
    public Cama (String nCama){
        this.nCama = nCama;
        this.doente = null;
    }
    
    //Seletores
    public String getNCama() {
        return nCama;
    }
    
    public String getDoente() {
        return doente;
    }
    
    public String getEstado() {
        return estado;
    }
    

    //Modificadores
    public void setNCama(String nCama) {
        this.nCama = nCama;
    }
    
    public void setDoente(String doente) {
        this.doente = doente;
    }
    
    public void setEstado() {
        if(getEstado().equals("Disponivel"))
            this.estado = "Ocupado";
        else
            this.estado = "Disponivel";
    }
    
    //Utilizar cama
    public void utilizar(String doente) {
        if(getEstado().equals("Disponivel")) {
            setDoente(doente);
            setEstado();
        }
    }
    
    //Habilitar cama
    public void habilitar() {
        setEstado();
        this.doente = null;
    }
    
    
    //toString
    @Override
    public String toString() {
        return "Estado" + estado + "Doente" + "nCama";
    }
}
