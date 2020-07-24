package BackEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaPS implements Serializable {
    
    public ArrayList<ProfissionalSaude> lista;
    
    //construtor
    public ListaPS() {
        lista = new ArrayList<ProfissionalSaude>();
    }
    
    // Seletores
    public ArrayList<ProfissionalSaude> getLista() {
        return lista;
    }
    
     public ProfissionalSaude getPs(String codigo) {
        for(ProfissionalSaude ps: lista) {
            if(ps.getCodigo().equals(codigo))
                return ps;
        }
        return null;
    }
    
    //Ordenar o nome por ordem alfabetica
    public List<ProfissionalSaude> ordenarNAsc() {
        Comparator<ProfissionalSaude> compara = new Comparator<ProfissionalSaude>() {
            @Override
            public int compare(ProfissionalSaude d1, ProfissionalSaude d2) {
                return d1.getNome().compareTo(d2.getNome());
            }
        };
        Collections.sort(lista, compara);
        return lista;
    }
    
    // Adicionar
    public void adicionar(ProfissionalSaude ps) {
        lista.add(ps);
    }
    
    // Existe
    public Boolean existe(String codigo) {
        for(ProfissionalSaude ps: lista) {
            if(ps.getCodigo().equals(codigo))
                return true;
        } 
        return false;
    }
    
    
    // Remover
    public void remover(String codigo) {
        for(int i = 0; i< lista.size(); i++) {
            if(lista.get(i).getCodigo().equals(codigo)) {
                lista.remove(i);
            }
        }
    }
    
    
    // ToString
    @Override
    public String toString() {
        String s = "Lista Profissionais de Saúde\n";
        for(ProfissionalSaude ps : lista) {
            s += ps.toString();
        }
        return s;
    }
}
