package FrontEnd;

import BackEnd.Cama;
import BackEnd.Doente;
import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DadosDoente extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Doente doente;
    private Hospital hospital;
    private ListarDoentes listagem;
    private String nCama;

    public DadosDoente(Sistema sistema, Serializacao bd, Doente doente, Hospital hospital, ListarDoentes listagem) throws ListaEnfermarias.EnfermariaNaoExistenteException {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.doente = doente;
        this.hospital = hospital;
        this.listagem = listagem;
        this.nCama = null;
 
        //Inserir as enfermarias existentes no hospital na comboBox        
        for (Enfermaria e: hospital.getLEnfermarias().todos()){
            cb_enfermaria.addItem(e.getCodigo());  
        }
  
        //Por os parametros de Numero de Cama invisiveis, so sera visivel se o doente selecionar uma enfermaria
        cb_nCamas.setVisible(false);
        lbl_nCamas.setVisible(false);
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //No caso de um registo novo
        if (registoNovo()) {    
            //Altera o titulo da janela
            setTitle("Adicionar um doente");
            txtCodigo.requestFocus();                      
        }else{
            //No caso de um registo existente
            //Altera o titulo da janela
            setTitle("Alterar dados do doente");
            //Preenche as caixas de texto com os dados do doente
            txtCodigo.setText(doente.getCodigo()); 
            txtCodigo.setEditable(false);
            txtNome.requestFocus();
        }
    }

    private boolean registoNovo() {
        //Se doente == null consideramos que é um novo registo
        return doente == null;
    } 
    
    private void guardar() throws DadosEmBranco, DadosInvalidos, ListaEnfermarias.EnfermariaNaoExistenteException {
        
        //Confirmar se os dados todos sao preenchidos
        Boolean flag1 = cb_estado.getSelectedItem().toString().equals("Selecione um estado:");
        Boolean flag2 = cb_enfermaria.getSelectedItem().toString().equals("Selecione uma enfermaria:");
        Boolean flag3 = cb_nCamas.getSelectedItem().toString().equals("Selecione um numero de cama:");
        
         if (txtCodigo.getText().isEmpty()|| txtNome.getText().isEmpty() || txtLocalidade.getText().isEmpty() || flag1 || flag2 || flag3) {
            JOptionPane.showMessageDialog(this, "Preencha todas caixas!");            
            txtCodigo.requestFocus();
            return;
        }
         
        //Obter todos os valores preenchido pelo utilizador
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        String localidade = txtLocalidade.getText();
        String cod_enfermaria = cb_enfermaria.getSelectedItem().toString();
        String estado = cb_estado.getSelectedItem().toString();
        String nCama = cb_nCamas.getSelectedItem().toString();
        
        
        if (!hospital.getLDoentes().existe(codigo)) {
        //O doente é novo
            txtCodigo.setEditable(true);
            Doente novo = new Doente(codigo, nome, localidade, cod_enfermaria, nCama, estado); 
            
            //Utilizar uma cama da enfermaria
            hospital.getLEnfermarias().getEnfermaria(cod_enfermaria).getLCamas().getCama(nCama).utilizar(codigo);
                    
            //Adicionar o doente a hospital
            hospital.getLDoentes().adicionar(novo);
        }else{
            
            //Tornar a cama livre se o doente mudar de enfermaria 
            if(doente.getEnfermaria() != cod_enfermaria) {
                //Oter a enfermaria onde o doente esta inserido inicialmente
                Enfermaria enfermariadoenteInserido = hospital.getLEnfermarias().getEnfermaria(doente.getEnfermaria());
                
                //Procurar a cama onde o doente esta inserido e torna a cama livre
                Cama cama = enfermariadoenteInserido.getLCamas().getCama(doente.getNumCama());
                cama.habilitar();
                
                ////Utilizar uma cama da nova enfermaria
                hospital.getLEnfermarias().getEnfermaria(cod_enfermaria).getLCamas().getCama(nCama).utilizar(codigo);
            }
            
            doente.setNome(nome);
            doente.setEstado(estado);
            doente.setLocalidade(localidade);
            doente.setEnfermaria(cod_enfermaria);
            doente.setCama(nCama);
            
            
            doente.setEstado(estado);
            
        }  
        listagem.preencheTabela();
        JOptionPane.showMessageDialog(this, "Doente guardado com sucesso.");
        guardarAlteracoes();
        fechar();    
    }
    
    public void guardarAlteracoes() {
        bd.guardar(sistema);
    }
    
    private void fechar() {
        dispose();
    }
    
    public void preencherCBCamas() throws ListaEnfermarias.EnfermariaNaoExistenteException {
        // Tornar o num cama visivel e obter o codigo de enfermaria selecionada pelo utilizador
        String cod_enfermaria = cb_enfermaria.getSelectedItem().toString();
        
        
        // Confirmar se foi selecionada uma enfermaria, se for torna o CB num de camas visivel
        if(!cod_enfermaria.equals("Selecione uma enfermaria:")) {
        cb_nCamas.setVisible(true);
        lbl_nCamas.setVisible(true);
             
        // Obter a enfermaria selecionada pelo utilizador
        Enfermaria enfermaria = hospital.getLEnfermarias().getEnfermaria(cod_enfermaria);
  
        //Remover todos os itens do comboBox inicial, para nao ter items repetidos
        int itemCount = cb_nCamas.getItemCount();

        for(int i=0;i<itemCount;i++){
            cb_nCamas.removeItemAt(0);
        }
        
        //Preencer a CB com a lista de camas disponíveis presentes nessa enfermaria
        for(Cama c: enfermaria.getLCamas().getLista()) {
            if(c.getEstado().equals("Disponivel"))
                cb_nCamas.addItem(c.getNCama());
        }

        }else
            JOptionPane.showMessageDialog(this, "Selecione todos os dados!");
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLocalidade = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        cb_estado = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cb_enfermaria = new javax.swing.JComboBox<>();
        cb_nCamas = new javax.swing.JComboBox<>();
        lbl_nCamas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(89, 136, 160));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jLabel1.setText("Dados do Doente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 630, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estado:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nome:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Localidade:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        jPanel1.add(txtLocalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 215, 28));
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 215, 28));
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 215, 28));

        cb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um estado:", "Moderado", "Grave", "Muito grave", " " }));
        jPanel1.add(cb_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 215, 28));

        btnGuardar.setBackground(new java.awt.Color(89, 136, 160));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        btnFechar.setBackground(new java.awt.Color(89, 136, 160));
        btnFechar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnFechar.setForeground(new java.awt.Color(255, 255, 255));
        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sair.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.setBorder(null);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        jPanel1.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enfermaria:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, 20));

        cb_enfermaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma enfermaria:" }));
        cb_enfermaria.setToolTipText("");
        cb_enfermaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_enfermariaActionPerformed(evt);
            }
        });
        jPanel1.add(cb_enfermaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 215, 28));

        cb_nCamas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escolhe um numero de cama:" }));
        cb_nCamas.setToolTipText("Seleciona uma enfermaria:");
        cb_nCamas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nCamasActionPerformed(evt);
            }
        });
        jPanel1.add(cb_nCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 215, 28));

        lbl_nCamas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_nCamas.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nCamas.setText("Nº cama:");
        jPanel1.add(lbl_nCamas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (DadosEmBranco | DadosInvalidos | ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(DadosDoente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        fechar();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void cb_enfermariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_enfermariaActionPerformed
        try {
            preencherCBCamas();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(DadosDoente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cb_enfermariaActionPerformed

    private void cb_nCamasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nCamasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_nCamasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cb_enfermaria;
    private javax.swing.JComboBox<String> cb_estado;
    private javax.swing.JComboBox<String> cb_nCamas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_nCamas;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtLocalidade;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
