package BackEnd;

import java.io.Serializable;
import java.time.LocalDateTime;


public class RegistoAcesso implements Serializable {
    
    //Variaveis de instancia
    private Utilizador utilizador;
    private LocalDateTime data;

    //Contrutores
    public RegistoAcesso() {  }

    public RegistoAcesso(Utilizador utilizador, LocalDateTime data) {
        this.utilizador = utilizador;
        this.data = data;
    }

    
    //Seletores
    public Utilizador getUtilizador() {
        return utilizador;
    }
    
    public LocalDateTime getData() {
        return data;
    }

    //Modificadores
    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }
    
    public void setData(LocalDateTime data) {
        this.data = data;
    }    
    
}
