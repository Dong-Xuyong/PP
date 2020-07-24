package BackEnd;

import java.io.Serializable;
import java.util.ArrayList;


public class ListaCamas implements Serializable {
    
    //Variaveis de instancia
    public ArrayList<Cama> lista;
 
    //Construtor 
    public ListaCamas() {
        lista = new ArrayList<Cama>();
        
    }
    
    // Seletores
    public ArrayList<Cama> getLista() {
        return lista;
    }
    
    public Cama getCama(String nCama) {
         for(Cama e: lista) {
             if(e.getNCama().equals(nCama))
                 return e;
         }
         return null;
     }
     
    
    // Numero de camas
    public int numero() {
        return lista.size();
    }
    
    //Adicionar camas
    public void adicionar(Cama cama){
        lista.add(cama);
    }
    
    // Numero de camas Ocupadas
    public int numeroOcupado() {
        int i = 0;
        for(Cama e: lista){
            if(e.getEstado().equals("Ocupado"));
                i++;
        }
        return i;
    } 
}
