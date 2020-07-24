package BackEnd;

import java.io.Serializable;
import java.util.Iterator;


public class Medico extends ProfissionalSaude implements Serializable {
	
    //Variaveis de instancia
    private String especialidade;
    private ListaDoentes lDoentes;
    private int nDoentes;
    private final String cargo = "Medico";
        
    //Constructor
    public Medico(String codigo, String nome, String enfermaria, String especialidade){
        super(codigo, nome , enfermaria);
        
        this.especialidade = especialidade;
        lDoentes = new ListaDoentes();
    }
         
    // Seletores
    public String getEspecialidade(){
	return especialidade;
    }
    
    @Override
    public String getCargo() {
        return cargo;
    }
    
    public ListaDoentes getLDoentes(){
        return lDoentes;
    }
    
    public int getNDoentes() {
        return nDoentes;
    }
    
    // Modificadores
    public void setEspecialidade(String especialidade){
    	this.especialidade= especialidade;
    }
    
    
    // adicinar doente na lista do medico
    public void adicionarDoente(Doente doente){
        lDoentes.adicionar(doente);
    }
    
    // remover doente na lista do medico
    public void removerDoente(Doente doente){
        
        Iterator<Doente> iter = lDoentes.getLista().iterator();
        
        while (iter.hasNext()) {
            Doente d = iter.next();
            if (d.getCodigo().equals(doente.getCodigo())) {
                iter.remove();
            }       
        }
    }
    
    //numero de doente na lista do medico
    public int numDoentes() {
        return lDoentes.numero();
    }
    
    // ToString
    @Override
    public String toString() {
        return "Medico\n" + "\nCódigo: " + this.getCodigo() + "Nome: " 
                + this.getNome() + "\nEnfermaria: " + this.getEnfermaria()
                + "\nEspecialidade: " + especialidade + "Número de Doentes: "
                + numDoentes();
    }
}
