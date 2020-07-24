package FrontEnd;

import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaCamas;
import BackEnd.Sistema;
import javax.swing.JOptionPane;
import Serializacao.Serializacao;

    public class DadosEnfermarias extends javax.swing.JFrame {
        
    //Variaveis de instancia   
    private Sistema sistema;
    private Serializacao bd;
    private Enfermaria enfermaria;
    private Hospital hospital;
    private ListaCamas lCamas;
    private ListarEnfermarias listagem;
   
    public DadosEnfermarias(Sistema sistema, Serializacao bd, Enfermaria enfermaria, Hospital hospital, ListarEnfermarias listagem) {
        //incializar os componentes
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.enfermaria = enfermaria;
        this.hospital = hospital;
        lCamas = new ListaCamas();
        this.listagem = listagem;

        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //No caso de um registo novo
        if (registoNovo()) {    
            //Altera o titulo da janela
            //Como construimos sempre uma nova janela, as caixas de texto já estão limpas
            setTitle("Criação de nova enfermaria");
            txtCodigo.requestFocus();                      
        }else{
            //No caso de um registo existente
            //Altera o titulo da janela
            setTitle("Alterar dados da enfermaria");
            //Preenche as caixas de texto com os dados do utilizador
            txtCodigo.setText(enfermaria.getCodigo()); 
            txtCodigo.setEditable(false);
            sp_nCama.setVisible(false);
            
        }              
    }
    

    private void guardar() {
         if (txtCodigo.getText().isEmpty() || cb_tipo.getSelectedItem().toString().equals("Selecione o tipo:")) {
            JOptionPane.showMessageDialog(this, "Preencha todas caixas!");            
            txtCodigo.requestFocus();
            return;
        }
        
        String codigo = txtCodigo.getText();
        String tipo = cb_tipo.getSelectedItem().toString();
        int nCamas = (int) sp_nCama.getValue();
        
        if (!hospital.getLEnfermarias().existe(codigo)) {      
            txtCodigo.setEditable(true);
            cb_tipo.setEditable(true);
            Enfermaria novo = new Enfermaria(codigo, tipo);
          
            hospital.getLEnfermarias().adicionar(novo);
            //Adcionar a quantidade de camas a lista de camas 
            novo.criarCamas(nCamas);
            listagem.atualizar();
            
        }else{
            txtCodigo.setEditable(false);
            txtCodigo.setText(enfermaria.getCodigo());
            enfermaria.setTipo(tipo);
            listagem.atualizar();
        }  
        
        JOptionPane.showMessageDialog(this, "Enfermaria guardada com sucesso.");
        guardarAlteracoes();
        fechar();    
    }
    
    private boolean registoNovo() {
        //Se enfermaria == null consideramos que é um novo registo
        return enfermaria == null;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnFechar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        cb_tipo = new javax.swing.JComboBox<>();
        sp_nCama = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(89, 136, 160));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jLabel5.setText("Dados da Enfermaria");
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
        jPanel1.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Código:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 92, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 85, 215, 28));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tipo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 146, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nº Camas:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        cb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo:", "Normal", "UCI" }));
        cb_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tipoActionPerformed(evt);
            }
        });
        jPanel1.add(cb_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 215, 28));

        sp_nCama.setModel(new javax.swing.SpinnerNumberModel(1, 1, 15, 1));
        jPanel1.add(sp_nCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 150, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        guardar();   
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cb_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cb_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner sp_nCama;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
