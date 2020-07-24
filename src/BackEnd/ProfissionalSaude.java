package BackEnd;

import java.io.Serializable;

public class ProfissionalSaude implements Serializable {
    
    //Variaveis de instancia
    private String codigo;
    private String nome;
    private String enfermaria;
    private final String cargo = "Enfermeiro";
    	
    // Constructor
    public ProfissionalSaude(String codigo, String nome, String enfermaria){
	this.codigo = codigo;
        this.nome = nome;
        this.enfermaria = enfermaria;
    }
    
    
    // Seletores
    public String getCodigo(){    
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEnfermaria() {    
        return enfermaria;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    
    // Modificador
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEnfermaria(String enfermaria) {
        this.enfermaria = enfermaria;
    }
        
    // toString
    @Override
    public String toString(){
                StringBuilder s1 = new StringBuilder();
            s1.append("***** DADOS DO PROFISSIONAL S *****\n");
            s1.append("Codigo : " + codigo + "\n");
            s1.append("Nome: " + nome + "\n");
            s1.append("Enfermaria: " + enfermaria + "\n");
            return s1.toString();
	}
}
