package FrontEnd;

import BackEnd.Doente;
import BackEnd.Equipamento;
import BackEnd.Hospital;
import BackEnd.ListaEquipamentos;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AssociarDoenteEquipamento extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private String cod_equipamento;
    private ListarEquipamentos listagem;

    public AssociarDoenteEquipamento(Sistema sistema, Serializacao bd, Hospital hospital, String cod_equipamento, ListarEquipamentos listagem) {
        initComponents();
        this.sistema= sistema;
        this.bd = bd;
        this.hospital = hospital;
        this.cod_equipamento = cod_equipamento;
        this.listagem = listagem;
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);                        
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        // Inserir a lista de doentes na comboBox
        for (int i=0; i < hospital.getLDoentes().getLista().size(); i++) {
            Doente doente = hospital.getLDoentes().getLista().get(i);
            cb_doente.addItem(doente.getCodigo());    
        }
    }
 
    private void Guardar() throws ListaEquipamentos.EquipamentoNaoExistenteException {
        if (cb_doente.getSelectedItem().toString().equals("Selecione uma opção:")) {
            JOptionPane.showMessageDialog(this, "Selecione o doente!"); 
            return;
        }
        
        String codigo_doente = (String)cb_doente.getSelectedItem();
        Equipamento equipamento = hospital.getLEquipamentos().getEquipamento(cod_equipamento);
        
        if (hospital.getLEquipamentos().existeEquipamento(equipamento)) {
            equipamento.utilizar(codigo_doente);
        }
        listagem.preencheTabela();
        JOptionPane.showMessageDialog(this, "Doente associado com sucesso.");
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

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        label_doente = new javax.swing.JLabel();
        cb_doente = new javax.swing.JComboBox<>();
        btn_fechar = new javax.swing.JButton();
        btn_alterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(253, 253, 247));

        jPanel3.setBackground(new java.awt.Color(253, 253, 247));

        jPanel4.setBackground(new java.awt.Color(89, 136, 160));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Que doente vai usar o equipamento?");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        label_doente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_doente.setText("Doente:");

        cb_doente.setBackground(new java.awt.Color(253, 253, 247));
        cb_doente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção:" }));

        btn_fechar.setBackground(new java.awt.Color(253, 253, 247));
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        btn_fechar.setBorder(null);
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });

        btn_alterar.setBackground(new java.awt.Color(253, 253, 247));
        btn_alterar.setText("Guardar");
        btn_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label_doente)
                        .addGap(18, 18, 18)
                        .addComponent(cb_doente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btn_fechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_doente)
                    .addComponent(cb_doente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_fechar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        fechar();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarActionPerformed
        try {
            Guardar();
        } catch (ListaEquipamentos.EquipamentoNaoExistenteException ex) {
            Logger.getLogger(AssociarDoenteEquipamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_alterarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alterar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JComboBox<String> cb_doente;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel label_doente;
    // End of variables declaration//GEN-END:variables
}
