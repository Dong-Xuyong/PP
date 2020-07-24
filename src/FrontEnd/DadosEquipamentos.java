package FrontEnd;

import BackEnd.Doente;
import BackEnd.Enfermaria;
import BackEnd.Equipamento;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DadosEquipamentos extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Equipamento equipamento;
    private Hospital hospital;
    private ListarEquipamentos listagem;
    private Doente doente;

           
    public DadosEquipamentos(Sistema sistema, Equipamento equipamento, Serializacao bd, Hospital hospital, Doente doente, ListarEquipamentos listagem) {
        //inicializar os componentes
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.equipamento = equipamento;
        this.hospital = hospital;
        this.listagem = listagem;
        this.doente = doente;

        if(registoNovo())
            txtCodigo.setEditable(true);
        else {
            txtCodigo.setText(equipamento.getCodigo()); 
            cb_tipo.setEditable(false);
            txtCodigo.setEditable(false);
        }
        
        // Inserir a lista de enfermarmarias na comboBox
        for (int i=0; i < hospital.getLEnfermarias().size(); i++) {
            Enfermaria enfermaria = hospital.getLEnfermarias().todos().get(i);
            cb_enfermaria.addItem(enfermaria.getCodigo());    
        }
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);     
    }
 
    private boolean registoNovo() {
        //Se equipamento == null consideramos que é um novo registo
        return equipamento == null;
    } 
    
    
    private void guardar() throws ListaEnfermarias.EnfermariaNaoExistenteException {
        if (txtCodigo.getText().isEmpty() || cb_enfermaria.getSelectedItem().toString().equals("Selecione enfermaria:") || cb_tipo.getSelectedItem().toString().equals("Selecione o tipo:")) {
            JOptionPane.showMessageDialog(this, "Preencha todas caixas!");            
            txtCodigo.requestFocus();
            return;
        }
        
        String codigo = txtCodigo.getText();
        String tipo = cb_tipo.getSelectedItem().toString();
        String enfermaria = cb_enfermaria.getSelectedItem().toString();
  
        if (!hospital.getLEquipamentos().existeCodigo(codigo)) {      
            txtCodigo.setEditable(true);
          
            Equipamento novo = new Equipamento(codigo, tipo, enfermaria, null);
            hospital.getLEquipamentos().adicionar(novo); 
        }else{
            txtCodigo.setEditable(false);
            txtCodigo.setText(equipamento.getCodigo());
            equipamento.setTipo(tipo);
            equipamento.setEnfermaria(enfermaria);        
        }  
        
        listagem.preencheTabela();
        JOptionPane.showMessageDialog(this, "Registo guardado com sucesso.");
        guardarAlteracoes();
        fechar(); 
    }
 
    private void fechar() {
        dispose();
    }
    
    private void guardarAlteracoes() {
        bd.guardar(sistema);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnFechar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        cb_enfermaria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cb_tipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(89, 136, 160));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jLabel5.setText("Dados dos Equipamentos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 275, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 47, 400, 10));

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
        jPanel1.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Código:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 210, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tipo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        cb_enfermaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione enfermaria:" }));
        cb_enfermaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_enfermariaActionPerformed(evt);
            }
        });
        jPanel1.add(cb_enfermaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 210, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enfermaria:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        cb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo:", "Agulha ", "Andador", "Autorrefrator", "Cateter", "Cistoscópio", "Colar cervical", "Colete ", "Desfibrilador", "Dreno", "Endoscópio", "Fórceps", "Nebulizador", "Oftalmoscópio", "Otoscópio", "Purificador de ar", "Seringa", "Ventilador" }));
        jPanel1.add(cb_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 210, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        fechar();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(DadosEquipamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cb_enfermariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_enfermariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_enfermariaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cb_enfermaria;
    private javax.swing.JComboBox<String> cb_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
