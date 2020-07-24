package FrontEnd;

import BackEnd.Doente;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Medico;
import BackEnd.ProfissionalSaude;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DadosDoenteMedico extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private ListarDoentesMedico listagem;
    private ProfissionalSaude medico;
 
    public DadosDoenteMedico(Sistema sistema, Serializacao bd, Hospital hospital, ProfissionalSaude medico, ListarDoentesMedico listagem) throws ListaEnfermarias.EnfermariaNaoExistenteException {
        //inicializar os componentes
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.medico = medico;
        this.hospital = hospital;
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

    //Guardar os dados
    private void guardar() throws DadosEmBranco, DadosInvalidos {
        if (cb_doente.getSelectedItem().toString().equals("Insira um doente")) {
            JOptionPane.showMessageDialog(this, "Preencha todas caixas!");            
            return;
         } else {
            String codigo = cb_doente.getSelectedItem().toString();
            //Adicionar o doente a lista de doentes do médico
            Doente doente = hospital.getLDoentes().getDoente(codigo);
            ((Medico)medico).adicionarDoente(doente);
        }
        listagem.preencheTabela();
        JOptionPane.showMessageDialog(this, "Doente registado com sucesso.");
        guardarAlteracoes();
        fechar();    
    }
    
    //Guarda os dados inseridos
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        cb_doente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(89, 136, 160));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jLabel1.setText("Dados do Doente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 400, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

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
        jPanel1.add(btnFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        cb_doente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Insira um doente" }));
        jPanel1.add(cb_doente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 180, 25));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Que doente quer inserir na lista do médico ? ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (DadosEmBranco | DadosInvalidos  ex) {
            Logger.getLogger(DadosDoenteMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        fechar();
    }//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cb_doente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
