package BackEnd;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;


public class ListaDoentes implements Serializable {
    
    //variaveis de instancia
    public final ArrayList<Doente> lista;

    
    //construtor
    public ListaDoentes() {
        lista = new ArrayList<Doente>();
    }
    
    // Seletores
    public ArrayList<Doente> getLista() {
        return lista;
    }
    
    public Doente getDoente(String codigo) {
        for (Doente e : lista ) {
            if (e.getCodigo().equals(codigo)) 
                return e;
        }
        return null;
    } 
    
    
    // Adicionar doente
    public void adicionar(Doente d) {
        lista.add(d);
    }
    
    // Apagar doente
    public void apagar(String codigo) {
        for(int i = 0; i< lista.size(); i++) {
            if(lista.get(i).getCodigo().equals(codigo)) {
                lista.remove(i);
            }
        }
    }
    
    // devolve ficha do doente
    public Doente ficha(String codigo) {
        for (Doente doente : lista) {
            if (doente.getCodigo().equals(codigo)) {
                return doente;
            }            
        }
        return null;
    }
    
     // Existe doente 
    public boolean existe(String codigo){
            for(Doente e: lista){
                if(e.getCodigo().equals(codigo) && codigo != null)
                    return true;
            }
            return false;
    }
    
    // Filtrar lista por enfermaria
    public ArrayList<Doente> filtrarEnfermaria(Enfermaria enfermaria){
        ListaDoentes listaF = new ListaDoentes();
        for(Doente d : lista) {
            if (d.getEnfermaria().equals(enfermaria.getCodigo()))
                listaF.adicionar(d);
        }
        return listaF.getLista();
    }
    
    // Filtrar lista por localidade
    public ArrayList<Doente> filtrarLocalidade(String localidade) {
        ListaDoentes listaF = new ListaDoentes();
        for(Doente d : lista) {
            if (d.getLocalidade().equals(localidade))
                listaF.adicionar(d);
        }
        return listaF.getLista();
    }
        
    // Filtrar lista por estado
    public ArrayList<Doente> filtrarEstado(String estado) {
        ListaDoentes listaF = new ListaDoentes();
        for(Doente d : lista) {
            if (d.getEstado().equals(estado))
                listaF.adicionar(d);
        }
        return listaF.getLista();
    }

    // Filtrar lista por data de entrada
    public ArrayList<Doente> filtrarDEntrada(LocalDate data) {
        ListaDoentes listaF = new ListaDoentes();
        for(Doente d : lista) {
            if (d.getDataEntrada().equals(data))
                listaF.adicionar(d);
        }
        return listaF.getLista();
    }
    
    
    //Ordenar o nome por ordem alfabetica
    public List<Doente> ordenarNAsc() {
        Comparator<Doente> compara = new Comparator<Doente>() {
            @Override
            public int compare(Doente d1, Doente d2) {
                return d1.getNome().compareTo(d2.getNome());
            }
        };
        Collections.sort(lista, compara);
        return lista;
    }
    
    
    //Ordenar o nome por ordem alfabetica descendente
    public List<Doente> ordenarNDesc() {
        Comparator<Doente> compara = new Comparator<Doente>() {
            @Override
            public int compare(Doente d1, Doente d2) {
                return d1.getNome().compareTo(d2.getNome());
            }
        };
        Collections.sort(lista, compara.reversed());
        return lista;
    }
    
    //Ordenar a lista por estado
    public List<Doente> ordenarEstado() {
        Comparator<Doente> compara = new Comparator<Doente>() {
            @Override
            public int compare(Doente o1, Doente o2) {
        int s1 = 0;
        int s2 = 0;

        if (o1.getEstado().equalsIgnoreCase("Alta")) {
            s1 = 1;
        }
        if (o1.getEstado().equalsIgnoreCase("Moderado")) {
            s1 = 2;
        }
        if (o1.getEstado().equalsIgnoreCase("Grave")) {
            s1 = 3;
        }
        if (o1.getEstado().equalsIgnoreCase("Muito Grave")) {
            s1 = 4;
        }
        if (o2.getEstado().equalsIgnoreCase("Alta")) {
            s2 = 1;
        }
        if (o2.getEstado().equalsIgnoreCase("Moderado")) {
            s2 = 2;
        }
        if (o2.getEstado().equalsIgnoreCase("Grave")) {
            s2 = 3;
        }
        if (o2.getEstado().equalsIgnoreCase("Muito Grave")) {
            s2 = 4;
        }
        if (s1 >= s2) {
            return 1;
        }
        if (s1 == s2) {
            return 0;
        }
        if (s1 <= s2) {
            return -1;
        }
        return 0;
    }
        };
        Collections.sort(lista, compara);
        return lista;
    }
    
    
    // Ordenar por data de entrada mais recente
    public List<Doente> ordenarDataA() {
        Comparator<Doente> compara = new Comparator<Doente>() {
            @Override
            public int compare(Doente d1, Doente d2) {
                return d1.getDataEntrada().compareTo(d2.getDataEntrada());
            }
        };
        Collections.sort(lista, compara);
        return lista;
    }
    
    
    // Ordenar por data de entrada mais antiga
    public List<Doente> ordenarDataR() {
        Comparator<Doente> compara = new Comparator<Doente>() {
            @Override
            public int compare(Doente d1, Doente d2) {
                return d1.getDataEntrada().compareTo(d2.getDataEntrada());
            }
        };
        Collections.sort(lista, compara.reversed());
        return lista;
    }
    
    
    // Numero de doentes
    public int numero() {
        int i = 0;
        for(Doente d : lista) {
            if(d.getDataSaida() == null)
                i += 1;
        }
        return i;
    }
    
    
    // numero de doentes graves
    public int numeroG() {
        int i = 0;
        for(Doente d : lista) {
            if((d.getDataSaida() == null) && (d.getEstado().equals("Grave")))
                i += 1;
        }
        return i;
    }
    

    // numero de doentes muito grave
    public int numeroMG() {
        int i = 0;
        for(Doente d : lista) {
            if((d.getDataSaida() == null) && (d.getEstado().equals("Muito grave")))
                i += 1;
        }
        return i;
    }
 

    // ToString
    @Override
    public String toString(){
        String s = "Lista Doentes\n";
        for (Doente doente : lista) {
            s += doente.toString();           
        }
        return s;
    }
}
