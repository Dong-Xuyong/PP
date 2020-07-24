package FrontEnd;

import BackEnd.Sistema;
import Serializacao.Serializacao;
import excetions.DadosEmBranco;
import excetions.DadosJaExistentes;

public class Piloto {      
    
     public static void main(String[] args) throws DadosJaExistentes, DadosEmBranco {
        Sistema sistema;        
        String ficheiroDados = String.format("%s\\utilizadores.data", System.getProperty("user.dir"));
        Serializacao bd = new Serializacao(ficheiroDados);        
        
        //Se o ficheiro de base de dados nao existir
        if (! bd.getFicheiro().exists()) {
            //Cria uma instancia do sistema
            sistema = new Sistema();      
            //inicializa o sistema adicionando 1 admin e 2 utilizadores
            sistema.inicializar();
            
        }else{
            sistema = bd.carregar();            
        }                 
        
        //abre a janela do login
        Login login = new Login(sistema, bd);               
        login.setVisible(true);
    }          
}
