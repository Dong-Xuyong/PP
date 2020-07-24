package BackEnd;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;


public class ListaEnfermarias implements Serializable {
   
    private TreeMap<String, Enfermaria> lista;
    
    public class EnfermariaNaoExistenteException extends Exception {
        public EnfermariaNaoExistenteException() { }
        public EnfermariaNaoExistenteException(String message) {
            super(message);
        }        
    }
    
    public class EnfermariaDuplicadoException extends Exception {
        public EnfermariaDuplicadoException() { }
        public EnfermariaDuplicadoException(String message) {
            super(message);
        }        
    }
    
    public ListaEnfermarias(){
        lista = new TreeMap<String, Enfermaria>();
    }
    
    public Enfermaria getEnfermaria(String codigo) throws EnfermariaNaoExistenteException {
        if (lista.containsKey(codigo)){
            return lista.get(codigo);
        }else{
            throw new EnfermariaNaoExistenteException("A enfermaria '%s' nao existe na lista");
        }
    }
    
    //devolver a lista
    public TreeMap<String, Enfermaria> getLista(){
        return lista;
    }
  
    //adicionar enfermarias
    public void adicionar(Enfermaria enfermaria){
        lista.put(enfermaria.getCodigo(), enfermaria);
    }
 
    //remover enfermaria
    public void remove(String codigo){
        lista.remove(codigo);
    }
    
    //verificar se a enfermaria existe
    public boolean existe(String codigo){
        return lista.containsKey(codigo);
    }
    
    //devolve o tamanho da lista
    public int size() {
        return lista.size();
    }
    
    // filtrar enfermaria 
    public ArrayList<Enfermaria> filtrarEnfermaria(String cod_enfermaria){
        
        ListaEnfermarias listaF = new ListaEnfermarias();
        
        for(Enfermaria e: this.todos()){
            if(e.getCodigo().equals(cod_enfermaria))
                listaF.adicionar(e);
        }
        return listaF.todos();
    }
    
    // transformar um treemap em arraylist
    public ArrayList<Enfermaria> todos() {
       return new ArrayList<>(lista.values());
    }
    
    // ToString
    @Override
    public String toString(){
        String s = "";
        for(Map.Entry me: lista.entrySet()){
            s += me.getValue().toString();
        }
        return s;
    }
}
