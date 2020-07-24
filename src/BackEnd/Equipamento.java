package BackEnd;

import java.io.Serializable;

public class Equipamento implements Serializable {
    
    // Variaveis de instancia
    private String codigo;
    private String tipo;
    private String estado = "Disponível";
    private String doente;
    private String enfermaria;
    
    // construtores
    public Equipamento () {}
 
    public Equipamento(String codigo, String tipo, String enfermaria, String doente) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.enfermaria = enfermaria;
        this.doente = doente;
    }
    
            
    // Seletores
    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }
    
    public String getEnfermaria() {
        return enfermaria;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public String getDoente() {
        return doente;
    }
    
    // Modificadores
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setEnfermaria(String enfermaria){
        this.enfermaria = enfermaria;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setDoente(String doente){
        this.doente = doente;
    }
    
    // Utilizar equipamento
    public void utilizar(String d) {
        setEstado("Ocupado");
        setDoente(d);
    }
    
    // Devolver o equipamento
    public void devolver() {
        setEstado("Disponível");
        setDoente(null);
    }
 
    // imprimir ficha
    public String toString(){
                StringBuilder s1 = new StringBuilder();
            s1.append("***** DADOS DA ENFERMARIA *****\n");
            s1.append("Codigo : " + codigo + "\n");
            s1.append("Tipo: " + tipo + "\n");
            s1.append("Enfermaria: " + enfermaria + "\n");
            s1.append("Estado: " + estado + "\n");
            return s1.toString();
    }  
}