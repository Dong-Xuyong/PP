package FrontEnd;

import BackEnd.Administrador;
import BackEnd.Equipamento;
import BackEnd.Hospital;
import BackEnd.ListaEquipamentos;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListarEquipamentos extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private DefaultTableModel modeloTabela;
    
    public ListarEquipamentos(Sistema sistema, Serializacao bd, Hospital hospital) {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.hospital = hospital;
        modeloTabela = (DefaultTableModel) tbl_equipamentos.getModel();
        tbl_equipamentos.setModel(modeloTabela);
        preencheTabela();
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        //Ocultar o menu
        pnl_menu.setVisible(false);
        btn_menuOff.setVisible(false);
        
        /**Administrador não consegue adicionar, editar ou eliminar equipamentos,
         * nem aceder ao menu */
        if (sistema.getUtilizadorLigado() instanceof Administrador) {
            btn_adicionar.setVisible(false);
            btn_editar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_menuOn.setVisible(false);
        } else {
            btn_adicionar.setVisible(true);
            btn_editar.setVisible(true);
            btn_eliminar.setVisible(true);
            btn_menuOn.setVisible(true);
        }
    }
    
    //preencher tabela
    public void preencheTabela() {
        modeloTabela.setRowCount(0); 
        for (Equipamento equip : hospital.getLEquipamentos().todos()) {
            if (equip.getEstado().equals("Ocupado")) {
                 String d = equip.getDoente();
            modeloTabela.addRow(new Object[]{equip.getCodigo(), equip.getTipo(), equip.getEstado(), equip.getEnfermaria(), d});  
            } else if (equip.getEstado().equals("Disponível")) {
                modeloTabela.addRow(new Object[]{equip.getCodigo(), equip.getTipo(), equip.getEstado(), equip.getEnfermaria(), ""});  
            } 
        }   
    }
    
    //adicionar equipamento
    private void adicionar() {
        DadosEquipamentos janela = new DadosEquipamentos (sistema, null, bd, hospital, null, this);   
        janela.setVisible(true);
    }

    //editar equipamento
    private void editar() throws ListaEquipamentos.EquipamentoNaoExistenteException  {
        int rowIndex = tbl_equipamentos.getSelectedRow();
        //Se nenhum registo selecionado, nao é possivel editar
        if (rowIndex == -1) return;
        
        String codigo = (String) modeloTabela.getValueAt(rowIndex, 0);
        
        Equipamento equipamento = hospital.getLEquipamentos().getEquipamento(codigo);
        DadosEquipamentos janela = new DadosEquipamentos(sistema, equipamento, bd, hospital, null, this);
        janela.setVisible(true);  
    }
  
    //eliminar equipamento
    private void eliminar()  {
        if (tbl_equipamentos.getSelectedRow() != -1) {
                int e = tbl_equipamentos.getSelectedRow();
                String codigo = tbl_equipamentos.getValueAt(e, 0).toString();
 
                hospital.getLEquipamentos().remove(codigo);
         
                modeloTabela.removeRow(e);
                JOptionPane.showMessageDialog(this, "Equipamento eliminado com sucesso.");
            }
    }
    
    public void atualizar() {                 
        modeloTabela.fireTableDataChanged();
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnl_menu = new javax.swing.JPanel();
        btn_utilizarEquipamento = new javax.swing.JButton();
        btn_equipamentoLivre = new javax.swing.JButton();
        btn_todosEquipamentos = new javax.swing.JButton();
        btn_devolverEquipamento = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_equipamentos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_adicionar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_menuOn = new javax.swing.JLabel();
        btn_menuOff = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_menu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        btn_utilizarEquipamento.setBackground(new java.awt.Color(255, 255, 255));
        btn_utilizarEquipamento.setText("Utilizar equipamento");
        btn_utilizarEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_utilizarEquipamentoActionPerformed(evt);
            }
        });

        btn_equipamentoLivre.setBackground(new java.awt.Color(255, 255, 255));
        btn_equipamentoLivre.setText("Equipamentos livres");
        btn_equipamentoLivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_equipamentoLivreMouseClicked(evt);
            }
        });
        btn_equipamentoLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_equipamentoLivreActionPerformed(evt);
            }
        });

        btn_todosEquipamentos.setBackground(new java.awt.Color(255, 255, 255));
        btn_todosEquipamentos.setText("Todos os equipamentos");
        btn_todosEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_todosEquipamentosActionPerformed(evt);
            }
        });

        btn_devolverEquipamento.setBackground(new java.awt.Color(255, 255, 255));
        btn_devolverEquipamento.setText("Devolver equipamento");
        btn_devolverEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_devolverEquipamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_menuLayout = new javax.swing.GroupLayout(pnl_menu);
        pnl_menu.setLayout(pnl_menuLayout);
        pnl_menuLayout.setHorizontalGroup(
            pnl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_todosEquipamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_equipamentoLivre, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btn_utilizarEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btn_devolverEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnl_menuLayout.setVerticalGroup(
            pnl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_utilizarEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_devolverEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btn_equipamentoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_todosEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel1.add(pnl_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 200, 160));

        tbl_equipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Tipo", "Estado", "Enfermaria", "Doente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_equipamentos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 480, 165));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/enfermaria.png"))); // NOI18N
        jLabel1.setText("Equipamentos deste Hospital:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        btn_adicionar.setBackground(new java.awt.Color(255, 255, 255));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 90, 30));

        btn_editar.setBackground(new java.awt.Color(255, 255, 255));
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 90, 30));

        jPanel2.setBackground(new java.awt.Color(89, 136, 160));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Painel de Equipamentos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 80));

        btn_fechar.setBackground(new java.awt.Color(253, 253, 247));
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        btn_fechar.setBorder(null);
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, -1, -1));

        btn_eliminar.setBackground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 90, 30));

        btn_menuOn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/menu.png"))); // NOI18N
        btn_menuOn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_menuOnMouseClicked(evt);
            }
        });
        jPanel1.add(btn_menuOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, -1));

        btn_menuOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/menu.png"))); // NOI18N
        btn_menuOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_menuOffMouseClicked(evt);
            }
        });
        jPanel1.add(btn_menuOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        try {
            editar();
        } catch (ListaEquipamentos.EquipamentoNaoExistenteException ex) {
            Logger.getLogger(ListarEquipamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_menuOnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_menuOnMouseClicked
        pnl_menu.setVisible(true);
        btn_menuOff.setVisible(true);
        btn_menuOn.setVisible(false);
    }//GEN-LAST:event_btn_menuOnMouseClicked

    private void btn_menuOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_menuOffMouseClicked
        pnl_menu.setVisible(false);
        btn_menuOn.setVisible(true);
        btn_menuOff.setVisible(false);
    }//GEN-LAST:event_btn_menuOffMouseClicked

    private void btn_utilizarEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_utilizarEquipamentoActionPerformed
        if(tbl_equipamentos.getSelectedRow() != -1){
            String codigo = tbl_equipamentos.getValueAt(tbl_equipamentos.getSelectedRow(), 0).toString();
            
            AssociarDoenteEquipamento listar = new  AssociarDoenteEquipamento(sistema, bd, hospital, codigo, this);
            listar.setVisible(true);
            preencheTabela();
        } else
           JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_btn_utilizarEquipamentoActionPerformed

    private void btn_devolverEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_devolverEquipamentoActionPerformed
       if(tbl_equipamentos.getSelectedRow() != -1){
            String codigo = tbl_equipamentos.getValueAt(tbl_equipamentos.getSelectedRow(), 0).toString();
          
            try {
                Equipamento e = hospital.getLEquipamentos().getEquipamento(codigo);
                e.devolver();
                preencheTabela();
                JOptionPane.showMessageDialog(this, "Equipamento devolvido com sucesso");
            } catch (ListaEquipamentos.EquipamentoNaoExistenteException ex) {
                Logger.getLogger(ListarEquipamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else
          JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_btn_devolverEquipamentoActionPerformed

    private void btn_equipamentoLivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_equipamentoLivreMouseClicked
        //listar na tabela só os equipamentos livres  
        modeloTabela.setRowCount(0);
        for (Equipamento e : hospital.getLEquipamentos().todos()) {
            if (e.getEstado().equals("Disponível"))
            modeloTabela.addRow(new Object[]{e.getCodigo(), e.getTipo(), e.getEstado(), e.getEnfermaria(), e.getDoente()});
        }
        JOptionPane.showMessageDialog(null, "Equipamentos livres listados.");
    }//GEN-LAST:event_btn_equipamentoLivreMouseClicked

    private void btn_equipamentoLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_equipamentoLivreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_equipamentoLivreActionPerformed

    private void btn_todosEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_todosEquipamentosActionPerformed
        preencheTabela();
    }//GEN-LAST:event_btn_todosEquipamentosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_devolverEquipamento;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_equipamentoLivre;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JLabel btn_menuOff;
    private javax.swing.JLabel btn_menuOn;
    private javax.swing.JButton btn_todosEquipamentos;
    private javax.swing.JButton btn_utilizarEquipamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_menu;
    public static javax.swing.JTable tbl_equipamentos;
    // End of variables declaration//GEN-END:variables
}
