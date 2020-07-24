package FrontEnd;

import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaDoentes;
import BackEnd.Medico;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import BackEnd.ProfissionalSaude;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DadosPS extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private final Sistema sistema;
    private final Serializacao bd;
    private final Hospital hospital;
    private final ProfissionalSaude ps;
    private ListarPS listagem;
    private ListaDoentes lDoentes;

    public DadosPS(Sistema sistema,Serializacao bd,Hospital hospital, ProfissionalSaude ps, ListaDoentes lDoentes, ListarPS listagem) {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.hospital = hospital;
        this.ps = ps;
        this.listagem = listagem;
        this.lDoentes = lDoentes;
        
         //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //Encher a comboBox das enfermarias
        for (int i=0; i < hospital.getLEnfermarias().size(); i++) {
            Enfermaria enfermaria = hospital.getLEnfermarias().todos().get(i);
            cb_enfermaria.addItem(enfermaria.getCodigo());    
        }
        
        //Esconde a comboBox e a label da especialidade
        cb_especialidade.setVisible(false);
        label_especialidade.setVisible(false);
        
        //No caso de um registo novo
        if (registoNovo()) {    
            //Altera o titulo da janela
            setTitle("Adicionar um Profissional de Saude");
            txtCodigo.requestFocus();                      
        }else{
            //No caso de um registo existente
            //Altera o titulo da janela
            setTitle("Alterar dados do Profissional de Saude");
            //Preenche as caixas de texto com os dados do utilizador
            txtCodigo.setText(ps.getCodigo()); 
            txtCodigo.setEditable(false);
            txtNome.requestFocus();
        }
    }
 
    private boolean registoNovo() {
        //Se enfermaria == null consideramos que é um novo registo
        return ps == null;
    } 
    
    private void guardar() throws DadosEmBranco, DadosInvalidos {
         if (txtCodigo.getText().isEmpty()|| txtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todas caixas!");            
            txtCodigo.requestFocus();
            return;
        }
   
        String codigo = txtCodigo.getText();
        String nome = txtNome.getText();
        
        String enfermaria = cb_enfermaria.getSelectedItem().toString();
        
        String cargo = cb_cargo.getSelectedItem().toString();
        
        if (!hospital.getLPS().existe(codigo)) {
            if(cargo.equals("Enfermeiro")) {
                ProfissionalSaude novo = new ProfissionalSaude(codigo, nome, enfermaria);
                hospital.getLPS().adicionar(novo);
                
            } else {
                cb_especialidade.setVisible(true);
                label_especialidade.setVisible(true);
            
                String especialidade = cb_especialidade.getSelectedItem().toString();
                ProfissionalSaude novo = new Medico(codigo, nome, enfermaria, especialidade); 
                hospital.getLPS().adicionar(novo);   
            }
            
        }else{
            if(cargo.equals("Enfermeiro")){
                txtCodigo.setText(ps.getCodigo()); 
                txtCodigo.setEditable(false);
                txtNome.requestFocus();
                ps.setNome(nome);
                ps.setEnfermaria(enfermaria);
               
            } else {
                txtCodigo.setText(ps.getCodigo()); 
                txtCodigo.setEditable(false);
                txtNome.requestFocus();
                ps.setNome(nome);
                ps.setEnfermaria(enfermaria);
            }
        }  
        listagem.preencheTabela();
        JOptionPane.showMessageDialog(this, "Profissional guardado com sucesso.");
        guardarAlteracoes();
        fechar();    
    }
    
    public void guardarAlteracoes() {
        bd.guardar(sistema);
    }
    
    private void fechar() {
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cb_enfermaria = new javax.swing.JComboBox<>();
        cb_cargo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cb_especialidade = new javax.swing.JComboBox<>();
        label_especialidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(89, 136, 160));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jLabel10.setText("Dados do Profissional de Saúde");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 400, 10));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Código:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nome:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 115, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel4.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 215, 28));

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel4.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 215, 28));

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
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

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
        jPanel4.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Enfermaria:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 155, -1, 20));

        cb_enfermaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_enfermariaActionPerformed(evt);
            }
        });
        jPanel4.add(cb_enfermaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 215, 28));

        cb_cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção", "Médico", "Enfermeiro" }));
        cb_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_cargoActionPerformed(evt);
            }
        });
        jPanel4.add(cb_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 215, 28));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Cargo:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 195, -1, 20));

        cb_especialidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção", "Anestesiologia", "Cardiologia", "Cardiologia Pediátrica", "Cirurgia Geral", "Cirurgia Pediátrica", "Cirurgia Plástica e Reconstrutiva e Estética", "Dermato-venereologia", "Doenças Infecciosas", "Endocrinologia-Nutrição", "Gastrenterologia", "Ginecologia-Obstetrícia", "Medicina Desportiva", "Medicina do trabalho", "Medicina Física e de Reabilitação", "Medicina Geral e Familiar", "Medicina Interna", "Medicina Legal", "Neuro-Cirurgia", "Neurologia", "Neuro-Radiologia", "Oftalmologia", "Oncologia Médica", "Ortopedia", "Otorrinolaringologia", "Pediatria", "Psiquiatria", "Radioterapia", "Reumatologia", "Saúde Pública", "Urologia" }));
        cb_especialidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_especialidadeActionPerformed(evt);
            }
        });
        jPanel4.add(cb_especialidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 215, 28));

        label_especialidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_especialidade.setForeground(new java.awt.Color(255, 255, 255));
        label_especialidade.setText("Especialidade:");
        jPanel4.add(label_especialidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 235, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        fechar();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (DadosEmBranco | DadosInvalidos ex) {
            Logger.getLogger(DadosDoente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void cb_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cargoActionPerformed
        if (cb_cargo.getSelectedItem().toString().equals("Médico"))
        {
            //Se for médico pede para indicar a especialidade
            cb_especialidade.setVisible(true);
            label_especialidade.setVisible(true);
        }
        else
        {
            //esconde a especialidade de o cargo for "enfermeiro"
            cb_especialidade.setVisible(false);
            label_especialidade.setVisible(false);
        }
    }//GEN-LAST:event_cb_cargoActionPerformed

    private void cb_especialidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_especialidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_especialidadeActionPerformed

    private void cb_enfermariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_enfermariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_enfermariaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cb_cargo;
    private javax.swing.JComboBox<String> cb_enfermaria;
    private javax.swing.JComboBox<String> cb_especialidade;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel label_especialidade;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
