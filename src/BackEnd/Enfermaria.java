package BackEnd;

import java.io.Serializable;

public class Enfermaria implements Serializable {
    
    //Variaveis de instancia
    private String codigo;
    private String tipo;
    private ListaPS lPS;
    private ListaEquipamentos LEquipamentos;
    private ListaCamas lCamas;
    private Cama cama;

    //Construtores
    public Enfermaria() {}
    
    public Enfermaria (String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
        lPS = new ListaPS();
        LEquipamentos = new ListaEquipamentos();
        lCamas = new ListaCamas();
    }
    
    // Seletores
    public String getCodigo() {
        return codigo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
     public String getNCamas() {
        return String.valueOf(lCamas.getLista().size());
    }
     
    public ListaPS getLPS() {
        return lPS;
    }
    
    public ListaEquipamentos getLEquipamentos() {
        return LEquipamentos;
    }
    
    public ListaCamas getLCamas() {
        return lCamas;
    }
    
    // Modificadores   
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

    public void setLPS(ListaPS lPS) {
        this.lPS = lPS;
    }

    public void setLEquipamentos(ListaEquipamentos LEquipamentos) {
        this.LEquipamentos = LEquipamentos;
    }
    
    // Criar camas
    public void criarCamas(int num){
         for(int i = 1; i<= num; i++) {
            cama = new Cama();
            cama.setNCama(String.valueOf(i));
            lCamas.adicionar(cama);
        }
         
    }
    
    // toString   
    @Override
    public String toString(){
                StringBuilder s1 = new StringBuilder();
            s1.append("***** DADOS DA ENFERMARIA *****\n");
            s1.append("Codigo : " + codigo + "\n");
            s1.append("Tipo: " + tipo + "\n");
            s1.append("Numero de camas: " + getNCamas());
            return s1.toString();
    }
    
}
