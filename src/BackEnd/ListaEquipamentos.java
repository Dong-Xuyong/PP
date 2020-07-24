package BackEnd;

import java.util.TreeMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;

public class ListaEquipamentos implements Serializable {
    
    public final TreeMap<String, Equipamento> lista;
    
    public class EquipamentoNaoExistenteException extends Exception {
        public EquipamentoNaoExistenteException() { }
        public EquipamentoNaoExistenteException(String message) {
            super(message);
        }        
    }
    
    public class EquipamentoDuplicadoException extends Exception {
        public EquipamentoDuplicadoException() { }
        public EquipamentoDuplicadoException(String message) {
            super(message);
        }        
    }
 
    // Construtor
    public ListaEquipamentos() {
        lista = new TreeMap<String, Equipamento>();
    }
    
    public Equipamento getEquipamento (String codigo) throws EquipamentoNaoExistenteException {
        if (lista.containsKey(codigo)){
            return lista.get(codigo);
        }else{
           throw new EquipamentoNaoExistenteException("O equipamento '%s' já existe na lista");
        }
    }
    
    // adiciona um equipamento
    public void adicionar(Equipamento equipamento) {
        lista.put(equipamento.getCodigo(), equipamento);    
    }
    
     // Getter
    public Equipamento getFicha(String codigo) {
        return lista.get(codigo);
    }
  
    // Existe codigo
    public boolean existeCodigo(String codigo) {
        return lista.containsKey(codigo);
    }
    
    // Existe Equipamento
    public boolean existeEquipamento(Equipamento e) {
        return lista.containsValue(e);
    }
    
    // remove equipamento 
    public void remove(String codigo) {
        lista.remove(codigo);
    }

    // Equipamentos livres
    public String livres() {
        String s = "Equipamentos livres\n";
        for (Map.Entry e : lista.entrySet()) {
            Equipamento eq = (Equipamento) e.getValue();
            if (eq.getEstado().equals("Disponível"))
            s += e.getValue().toString(); 
        }
        return s;
    }
    
    // Numero de equipamentos
    public int numero() {
        return lista.size();
    }
    
    // Numero de equipamentos ocupados
    public int numeroOcupado() {
        int i = 0;
        for (Map.Entry e : lista.entrySet()) {
            Equipamento eq = (Equipamento) e.getValue();
            if (eq.getEstado().equals("Ocupado"))
                i++;
        }
        return i;
    }
    
    // Numero de tipo de equipamento 
    public int numeroTipo(String tipo) {
        int i = 0;
        for (Map.Entry e : lista.entrySet()) {
            Equipamento eq = (Equipamento) e.getValue();
            if(tipo == eq.getTipo())
                i++;
        }
        return i;
    }
    
    // Numero de tipo equipamento ocupado
    public int numeroTipoOcupado(String tipo) {
        int i = 0;
        for (Map.Entry e : lista.entrySet()) {
            Equipamento eq = (Equipamento) e.getValue();
            if(tipo == eq.getTipo() && eq.getEstado().equals("Ocupado"))
                i++;
        }
        return i;
    }
    
    // transformar um TreeMap em ArrayList
    public ArrayList<Equipamento> todos() {
       return new ArrayList<>(lista.values());
    }
        
    // toString
    @Override
    public String toString() {
        String s = "";
        for(Map.Entry me : lista.entrySet()) {
            s += me.getValue().toString(); 
        }
        return s;
    } 
}
