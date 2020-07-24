package BackEnd;

import excetions.DadosEmBranco;
import java.io.Serializable;

public class Administrador extends Utilizador implements Serializable {
        
        //Construtores
        public Administrador() { } 
        
	public Administrador(String username, String pw, String nome)throws DadosEmBranco {
		super(username, pw, nome);
	}

        //toString
        @Override
	public String toString(){
		return "Administrador: "+super.toString()+"\n";
        }     
}
