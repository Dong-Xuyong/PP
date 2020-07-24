package BackEnd;

import java.io.Serializable;

//Para    cada    hospital    adicionar/apagar    enfermarias,    equipamentos, doentes, profissionais de saúde
public class Hospital implements Serializable {
    
    //Variaveis de instancia
    private String codigo;
    private String nome;
    private String localidade;
    private ListaEnfermarias lEnfermarias;
    private ListaDoentes lDoentes;
    private ListaEquipamentos lEquipamentos;
    private ListaPS lPS;
    
    //Construtor
    public Hospital (String codigo, String nome, String localidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.localidade = localidade;
        lEnfermarias = new ListaEnfermarias();
        lDoentes = new ListaDoentes();
        lEquipamentos = new ListaEquipamentos();
        lPS = new ListaPS();
    }
    
   
    // Seletores
    public String getCodigo() {
        return codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getLocalidade() {
        return localidade;
    }
    
    public ListaEnfermarias getLEnfermarias() {
        return lEnfermarias;
    }

    public ListaEquipamentos getLEquipamentos() {
        return lEquipamentos;
    }

    public ListaDoentes getLDoentes() {
        return lDoentes;
    }

    public ListaPS getLPS() {
        return lPS;
    }
    
    // Modificadores
    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }
    
    public void setNome (String nome) {
        this.nome = nome;
    }
     public void setLocalidade (String localidade) {
        this.localidade = localidade;
    }
    
     public void setLEnfermarias(ListaEnfermarias lEnfermarias) {
        this.lEnfermarias = lEnfermarias;
    }

    public void setLEquipamentos(ListaEquipamentos lEquipamentos) {
        this.lEquipamentos = lEquipamentos;
    }

    public void setLDoentes(ListaDoentes lDoentes) {
        this.lDoentes = lDoentes;
    }

    public void setLPS(ListaPS lPS) {
        this.lPS = lPS;
    }
    
    // Maior numero de camas ocupadas na lista de enfermaria  
    public int MaxNCamasOcupadas() {
        int max = 0;
       
        for(Enfermaria e: lEnfermarias.todos()) {
            if(e.getLCamas().numeroOcupado() > max)
                max = e.getLCamas().numeroOcupado();
        }
        return max;
    }
    
    // Enfermarias com numero max de camas ocupadas
    public ListaEnfermarias EnfermariaMaxNCamasOcupadas() {
       String max = String.valueOf(MaxNCamasOcupadas());
       ListaEnfermarias lenfer = new ListaEnfermarias();
       
       for(Enfermaria e: lEnfermarias.todos()) {
           if(e.getNCamas().equals(max))
               lenfer.adicionar(e);
       }
       return lenfer;
    }
                              
    // Enfermaria Maior numero de equipamentos ocupados na lista de Enfermaria
    public int MaxEqupamentosOcupados() {
        int max = 0;
        for(Enfermaria e: lEnfermarias.todos()) {
            if(e.getLEquipamentos().numeroOcupado() > max)
                System.out.println(e.getLEquipamentos().numeroOcupado());
                max = e.getLEquipamentos().numeroOcupado();
        }
        return max;
    }

    //Percentagem de doentes graves
    public String percentagemDoentesG() {
        int total = lDoentes.numero();
        int grave = lDoentes.numeroG();
        float graveP = (grave * 100) / total;
        return String.valueOf(graveP) + "%";
    }
    
    //Percentagem de doentes muito graves
    public String percentagemDoenteMG() {
        int total = lDoentes.numero();
        int mGrave = lDoentes.numeroMG();
        float mGraveP = (mGrave * 100) / total;
        return String.valueOf(mGraveP) + "%";
    }
    
    
    // Percentagem tipo equipamentos ocupados
    public String PercemtagemTipoEquipamentoOcupado(String tipo) {
        int total = lEquipamentos.numeroTipo(tipo);
        int ocupado = lEquipamentos.numeroTipoOcupado(tipo);
        float porcentagem = (float) (ocupado * 100) / total;
        return String.valueOf(porcentagem) + "%";
    }
    
    // Enfermaria mais problemática
    public String EnfermariaProblematica() {
        int max = 0;
        
        ListaEnfermarias lenferarias = EnfermariaMaxNCamasOcupadas();
        String cod_enfermaria = "";
        //Verificar qual dos enfermarias tem maior numero de equipamentos ocupados
        for(Enfermaria e: lenferarias.todos()) {
            if(e.getLEquipamentos().numeroOcupado()>= max)
                cod_enfermaria = e.getCodigo();
                }
      return cod_enfermaria;
    }
      
    //toString
    @Override
    public String toString() {
        return "Hospital: \n" + "Codigo = " + codigo + "\n" + "Nome = " + nome + "\n" + "Localidade = " + localidade + "\n";
    }
}
            