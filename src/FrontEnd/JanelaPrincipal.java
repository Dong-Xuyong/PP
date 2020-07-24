package FrontEnd;

import BackEnd.Administrador;
import BackEnd.Equipamento;
import BackEnd.Sistema;
import BackEnd.Utilizador;
import javax.swing.JOptionPane;
import Serializacao.Serializacao; 

public class JanelaPrincipal extends javax.swing.JFrame {

    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Utilizador utilizador;
    private Equipamento equipamento;
    
    public JanelaPrincipal(Sistema sistema, Serializacao bd, Utilizador utilizador) {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.utilizador = utilizador;
        this.equipamento = equipamento;
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //Apenas mostra o menu de administração se o utilizador for um administrador
        btn_administracao.setVisible(sistema.getUtilizadorLigado() instanceof Administrador);
    }
    
    public void guardarAlteracoes() {
        bd.guardar(sistema);
    }
    
    private void alterarPassword() {        
        DadosUtilizador psw = new DadosUtilizador(sistema, sistema.getUtilizadorLigado(), null, bd);
        psw.setVisible(true);
    }
    
    //da acesso a lista de utilizadores
    private void ListaUtilizadores() {
        ListarUtilizadores lista = new ListarUtilizadores (sistema, bd);
        lista.setVisible(true);
    }
    
    //da acesso ao perfil do utilizador
    private void PerfilUtilizador () {
        Perfil perfil = new Perfil (sistema, utilizador, bd);
        perfil.setVisible(true);
    }
    
    //da acesso a lista de hospitais
    private void ListaHospitais() {
        ListarHospital lista = new ListarHospital (sistema, bd);
        lista.setVisible(true);
    }
    
    private void terminar() {
        Object[] opçoes = {"Sim", "Não"}; 
        int resposta  = JOptionPane.showOptionDialog(this, "Deseja mesmo sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
        null, opçoes, opçoes[0]);

        if (resposta == JOptionPane.YES_OPTION) //opção escolhida sim, sai
            System.exit(0);
    }
    
    //da acesso a Dashboard
    private void DashBoard() {
        Dashboard dashboard = new Dashboard(sistema);
        dashboard.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_hospital = new javax.swing.JButton();
        btn_utilizador = new javax.swing.JButton();
        btn_administracao = new javax.swing.JButton();
        btn_dashboard = new javax.swing.JButton();
        btn_terminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jSeparator1.setBackground(new java.awt.Color(16, 64, 88));
        jSeparator1.setForeground(new java.awt.Color(16, 64, 88));
        jSeparator1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 64, 88), 1, true));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(16, 64, 88));
        jLabel2.setText("Bem Vindo!");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(16, 64, 88));

        btn_hospital.setBackground(new java.awt.Color(253, 253, 247));
        btn_hospital.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btn_hospital.setForeground(new java.awt.Color(16, 64, 88));
        btn_hospital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/hospital2.png"))); // NOI18N
        btn_hospital.setText("Hospitais");
        btn_hospital.setMaximumSize(new java.awt.Dimension(125, 29));
        btn_hospital.setMinimumSize(new java.awt.Dimension(125, 29));
        btn_hospital.setPreferredSize(new java.awt.Dimension(125, 29));
        btn_hospital.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hospitalMouseClicked(evt);
            }
        });
        btn_hospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hospitalActionPerformed(evt);
            }
        });

        btn_utilizador.setBackground(new java.awt.Color(253, 253, 247));
        btn_utilizador.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btn_utilizador.setForeground(new java.awt.Color(16, 64, 88));
        btn_utilizador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/utilizador.png"))); // NOI18N
        btn_utilizador.setText("Perfil");
        btn_utilizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_utilizadorActionPerformed(evt);
            }
        });

        btn_administracao.setBackground(new java.awt.Color(253, 253, 247));
        btn_administracao.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btn_administracao.setForeground(new java.awt.Color(16, 64, 88));
        btn_administracao.setText("Administração");
        btn_administracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_administracaoActionPerformed(evt);
            }
        });

        btn_dashboard.setBackground(new java.awt.Color(253, 253, 247));
        btn_dashboard.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        btn_dashboard.setForeground(new java.awt.Color(16, 64, 88));
        btn_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/dashboard.png"))); // NOI18N
        btn_dashboard.setText("Dashboard");
        btn_dashboard.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        btn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dashboardActionPerformed(evt);
            }
        });

        btn_terminar.setBackground(new java.awt.Color(253, 253, 247));
        btn_terminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/logout.png"))); // NOI18N
        btn_terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_terminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_terminar, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_utilizador, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_hospital, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(btn_administracao, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btn_utilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btn_administracao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_terminar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 270, 400));

        jLabel4.setBackground(new java.awt.Color(253, 253, 247));
        jLabel4.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(253, 253, 247));
        jLabel4.setText("Todos os dias a cuidar de si!");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/mb.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 560, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hospitalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hospitalMouseClicked

    }//GEN-LAST:event_btn_hospitalMouseClicked

    private void btn_hospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hospitalActionPerformed
        ListaHospitais();
    }//GEN-LAST:event_btn_hospitalActionPerformed

    private void btn_utilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_utilizadorActionPerformed
        PerfilUtilizador();
    }//GEN-LAST:event_btn_utilizadorActionPerformed

    private void btn_administracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_administracaoActionPerformed
        ListaUtilizadores();
    }//GEN-LAST:event_btn_administracaoActionPerformed

    private void btn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboardActionPerformed
        DashBoard();
    }//GEN-LAST:event_btn_dashboardActionPerformed

    private void btn_terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_terminarActionPerformed
        guardarAlteracoes();
        terminar();
    }//GEN-LAST:event_btn_terminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_administracao;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_hospital;
    private javax.swing.JButton btn_terminar;
    private javax.swing.JButton btn_utilizador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
