package BackEnd;

import excetions.DadosEmBranco;
import excetions.DadosJaExistentes;
import excetions.DadosNaoEncontrados;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class ListaUtilizadores implements Serializable {
    
    private TreeMap <String, Utilizador> lista;
    
    static class UtilizadorDuplicadoException extends Exception {

        public UtilizadorDuplicadoException() {
        }
    }
    
    //Construtor
    public ListaUtilizadores(){
        this.lista = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }
   
    //Adicionar utilizador
    public void adicionar(Utilizador u) throws DadosJaExistentes, DadosEmBranco {
        if (u == null) {
            throw new DadosEmBranco("O parâmetro 'utilizador' não pode ser um valor nulo");
        }        
        
        if (!lista.containsKey(u.getUsername())) {
            lista.put(u.getUsername(), u);
        }else{
            throw new DadosJaExistentes(String.format("O utilizador '%s' já existe na coleção", u.getUsername()));
        }  
    } 
    
    //Existe utilizador
    public boolean existe(String username) {
        return lista.containsKey(username);
    }
    
    //Retorna o tamanho da lista
    public int size() {
        return lista.size();
    }
    
    //Utilizador ligado no sistema
    public Utilizador getUtilizador(String username) throws DadosNaoEncontrados {
        if (lista.containsKey(username)){
            return lista.get(username);
        }else{
            throw new DadosNaoEncontrados("O utilizador '%s' já existe na lista");
        }
    }
    
    //Remover utilizador
    public void remover(String username) throws DadosNaoEncontrados {
        if(lista.containsKey(username)){
        lista.remove(username);
        }else{
            throw new DadosNaoEncontrados("O utilizador '%s' já existe na lista");
        }
    }
    
        
    public ArrayList<Utilizador> todos() {
        return new ArrayList<>(lista.values());
    } 
}
