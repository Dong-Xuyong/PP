package BackEnd;

import excetions.DadosEmBranco;
import java.io.Serializable;

public class Utilizador implements Serializable {
    
        //Variaveis de instancia
	private String username;
        private String pw;
	private String nome;
        
        // Construtores
        public Utilizador() { }
        
	public Utilizador(String username, String pw, String nome) throws DadosEmBranco {
		this.username = username;
                this.pw = pw;
                this.nome = nome;
	}
	
        // Seletores
	public String getUsername() {
		return username;
	}
	
	public String getNome(){
		return nome;
	}
        
        public String getPw(){
		return pw;
	}
        
       // Modificadores
	public void setUsername(String username) throws DadosEmBranco {
            if(username.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.username= username;
            }
        }
	
	public void setNome(String nome) throws DadosEmBranco {
            if(nome.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.nome = nome;
            }
        }
		   
        public void setPw(String pw) throws DadosEmBranco {
            if(pw.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.pw = pw;
            }
        }
        
	// ToString
	public String toString(){
                StringBuilder s1 = new StringBuilder();
            s1.append("***** DADOS DO UTILIZADOR *****\n");
            s1.append("Username : " + username + "\n");
            s1.append("Password: " + pw + "\n");
            s1.append("Nome: " + nome + "\n");
            return s1.toString();
	}
}
