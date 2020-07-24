package BackEnd;

import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.ArrayList;

public class ListaHospitais implements Serializable {
    
    private TreeMap<String, Hospital> lista;
    
    public class HospitalNaoExistenteException extends Exception {
        public HospitalNaoExistenteException() { }
        public HospitalNaoExistenteException(String message) {
            super(message);
        }        
    }

    public class HospitalDuplicadoException extends Exception {
        public HospitalDuplicadoException() { }
        public HospitalDuplicadoException(String message) {
            super(message);
        }        
    }
    
    public Hospital getHospital(String h) throws HospitalNaoExistenteException {
        if (lista.containsKey(h)){
            return lista.get(h);
        }else{
            throw new HospitalNaoExistenteException("O hospital '%s' já existe na lista");
        }
    }
    
    public ListaHospitais(){
        lista = new TreeMap<String, Hospital>();
    }

    //devolver a lista
    public TreeMap<String, Hospital> getLista(){
        return lista;
    }
    
    //adicionar hospitais
    public void adicionar(Hospital hospital){
        lista.put(hospital.getCodigo(), hospital);
    }
    
    //remover hospital 
    public void remove(String codigo){
        lista.remove(codigo);
    }
    
   //verificar se o hospital existe
    public boolean existeHospital(Hospital h){
        return lista.containsValue(h);
    }
    
    //Retorna o tamanho da lista
    public int size() {
        return lista.size();
    }
    
    //transformar um TreeMap em ArrayList
    public ArrayList<Hospital> todos() {
       return new ArrayList<>(lista.values());
    }
    
    //toString
    @Override
    public String toString(){
        String s = "Lista Hositais\n";
        for(Map.Entry me: lista.entrySet()){
            s += me.getValue().toString();
        }
        return s;
    }
}
