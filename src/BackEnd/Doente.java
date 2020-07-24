package BackEnd;

import java.io.Serializable;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.time.LocalDate;

public class Doente implements Serializable {
    
    //Variaveis de instancia
    private String codigo;
    private String nome;
    private String localidade;
    private String enfermaria;
    private String nCama;
    private String estado;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    
    //Construtores
    public Doente() {} 
    
    public Doente(String codigo, String nome, String localidade, String enfermaria, String nCama, String estado) throws DadosEmBranco, DadosInvalidos  {
        this.codigo = codigo;
        this.nome = nome;
        this.localidade = localidade;
        this.enfermaria = enfermaria;
        this.estado = estado;
        this.nCama = nCama;
        dataEntrada = LocalDate.now();
    }
    
    //Seletores
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getEnfermaria() {
        return enfermaria;
    }

    public String getNumCama() {
        return nCama;
    }

    public String getEstado() {
        return estado;
    }
    
    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }
    
    // Modificadores
    public void setCodigo(String codigo) throws DadosEmBranco, DadosInvalidos {
        if(codigo.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.codigo= codigo;
            }
        }

    public void setNome(String nome) throws DadosEmBranco, DadosInvalidos {
    if(nome.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.nome= nome;
        }
    }

    public void setLocalidade(String localidade) throws DadosEmBranco, DadosInvalidos {
       if(localidade.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.localidade= localidade;
            }
        }

    public void setEnfermaria(String enfermaria) throws DadosEmBranco {
        if(enfermaria == null) {
                throw new DadosEmBranco();
            } else {
		this.enfermaria= enfermaria;
            }
        }

    public void setCama(String nCama) throws DadosEmBranco, DadosInvalidos {
        if(nCama == null) {
                throw new DadosEmBranco();
            } else {
		this.nCama= nCama;
            }
        }
    
    public void setEstado(String estado) throws DadosEmBranco, DadosInvalidos {
        if(estado.isEmpty()) {
                throw new DadosEmBranco();
            } else {
		this.estado= estado;
            }
        }
    
    public void setDataEntrada(String dataEntrada) {
        LocalDate date = LocalDate.parse(dataEntrada);
        this.dataEntrada = date;
    }

    public void setDataSaida(String dataSaida) {
        LocalDate date = LocalDate.parse(dataSaida);
        this.dataSaida = date;
    }
    
    
    // Alta
    public void alta() {
        this.estado = "Alta";
        this.dataSaida = LocalDate.now();
    }

    // ToString
    @Override
    public String toString() {
        if (dataSaida == null)
            return "Doente\n" + "Código: " + codigo + "\nNome: " + nome
                    + "Localidade: " + localidade + "\nEnfermaria: " +
                    enfermaria + "Estado: " + estado + "Entrada: " + 
                    dataEntrada;        
        return "Doente\n" + "Código: " + codigo + "\nNome: " + nome
                    + "Localidade: " + localidade + "\nEnfermaria: " +
                    enfermaria + "Estado: " + estado + "Entrada: " + 
                    dataEntrada + "Saída: " + dataSaida;
    }      
}