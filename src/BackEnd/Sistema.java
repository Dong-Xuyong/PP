package BackEnd;

import excetions.DadosEmBranco;
import excetions.DadosJaExistentes;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Sistema implements Serializable {
    
    //Variaveis de instancia
    private final ListaUtilizadores utilizadores;
    private final List<RegistoAcesso> listaEntradas;
    private Utilizador utilizadorLigado;
    private final ListaDoentes doentes;            
    private final ListaEquipamentos equipamentos;
    private final ListaHospitais hospitais;

    //Construtor
    public Sistema() {
        utilizadores = new ListaUtilizadores();   
        listaEntradas = new ArrayList<>(); 
        doentes = new ListaDoentes();
        equipamentos = new ListaEquipamentos();
        hospitais = new ListaHospitais();       
    }                       

    //Seletores
    public ListaUtilizadores getListaUtilizadores() {
        return utilizadores;
    }
    
    public List<RegistoAcesso> getListaEntradas() {
        return listaEntradas;
    }
    
    public ListaDoentes getDoentes() {
        return doentes;
    }

    public ListaEquipamentos getEquipamentos() {
        return equipamentos;
    }

    public ListaHospitais getHospitais() {
        return hospitais;
    }
    
    public Utilizador getUtilizadorLigado() {
        return utilizadorLigado;
    }
    
    //Autenticar utilizador no login
    public boolean autenticarUtilizador(String username, String password) {        
        if (utilizadores.existe(username)) {
            try{
                Utilizador u = utilizadores.getUtilizador(username);                
                if (u.getPw().equals(password)){
                    utilizadorLigado = u;
                    listaEntradas.add(new RegistoAcesso(u, LocalDateTime.now()));
                    return true;
                }                
            }catch (Exception e) {}                        
        }        
        return false;        
    }
    
    //Inicializar o sistema
    public void inicializar() throws DadosJaExistentes, DadosEmBranco {
        utilizadores.adicionar(new Administrador("admin", "admin", "Aministrador"));
        utilizadores.adicionar(new Utilizador("user1", "1234", "Utilizador 1"));
        utilizadores.adicionar(new Utilizador("user2", "1234", "Utilizador 2")); 
    }
    
    //Terminar
    public void terminar() {
        System.exit(0);
    }   
}
