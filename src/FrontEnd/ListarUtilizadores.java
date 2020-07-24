package FrontEnd;

import BackEnd.Sistema;
import BackEnd.Utilizador;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import Serializacao.Serializacao;
import excetions.DadosNaoEncontrados;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListarUtilizadores extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private AbstractTableModel modeloTabela;
    private ListarUtilizadores listagem;
    private Serializacao bd;
    
    public ListarUtilizadores(Sistema sistema, Serializacao bd) {
        initComponents();
        this.sistema = sistema;
        this.modeloTabela = criarModeloTabela();
        this.bd = bd;
        tbl_utilizadores.setModel(modeloTabela);
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }
  
    //criar tabela abstrata
    private AbstractTableModel criarModeloTabela() {   
        String[] nomeColunas = {"Nome", "Username"};
        
        return new AbstractTableModel() {     
            @Override
            public String getColumnName(int column) {
                return nomeColunas[column];
            }
           
            @Override
            public int getRowCount() {
                //Retorna o número de linhas que a tabela deverá ter
                return sistema.getListaUtilizadores().size();
            }

            @Override
            public int getColumnCount() {
                //Retorna o número de colunas que a tabela deverá ter
                return nomeColunas.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
            // vai buscar os valores a partir de uma linha e uma coluna
                switch (columnIndex) {
                    case 0: 
                        return sistema.getListaUtilizadores().todos().get(rowIndex).getNome();
                    case 1:
                        return sistema.getListaUtilizadores().todos().get(rowIndex).getUsername();
                    default:
                        return "";
                }                              
            }            
        };
    }
    
    //adicionar utilizador
    private void adicionar() {
        DadosUtilizador janela = new DadosUtilizador(sistema, null, listagem, bd);   
        janela.setVisible(true);
    }
    
    //editar utilizador
    private void editar() throws DadosNaoEncontrados {
        int rowIndex = tbl_utilizadores.getSelectedRow();
        //Se nenhum registo selecionado, nao é possivel editar
        if (rowIndex == -1) return;
        
        String username = (String) modeloTabela.getValueAt(rowIndex, 1);

            Utilizador utilizador;
            utilizador = sistema.getListaUtilizadores().getUtilizador(username);
            DadosUtilizador janela = new DadosUtilizador(sistema, utilizador, this, bd);   
            janela.setVisible(true);   
    }
     
    //eliminar utilizador
    private void eliminar() throws DadosNaoEncontrados {
        if (tbl_utilizadores.getSelectedRow() != -1) {
                int u = tbl_utilizadores.getSelectedRow();
                String username = tbl_utilizadores.getValueAt(u, 1).toString();
 
                sistema.getListaUtilizadores().remover(username); 
           
                modeloTabela.fireTableRowsDeleted(tbl_utilizadores.getSelectedRow(), 0);
                JOptionPane.showMessageDialog(this, "Utilizador eliminado com sucesso.");
            }
    }
    
    //Informa o modelo que foram efetuadas alterações, o modelo informa a tabela e os dados são redesenhados
    public void atualizar() {                 
        modeloTabela.fireTableDataChanged();
    } 
    
    private void fechar() {
        dispose();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_utilizadores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnl_menu = new javax.swing.JPanel();
        btn_adicionar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_utilizadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Username"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_utilizadores);
        if (tbl_utilizadores.getColumnModel().getColumnCount() > 0) {
            tbl_utilizadores.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbl_utilizadores.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 165));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/utilizador.png"))); // NOI18N
        jLabel1.setText("Utilizadores Registados no Sistema:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        pnl_menu.setBackground(new java.awt.Color(253, 253, 247));

        javax.swing.GroupLayout pnl_menuLayout = new javax.swing.GroupLayout(pnl_menu);
        pnl_menu.setLayout(pnl_menuLayout);
        pnl_menuLayout.setHorizontalGroup(
            pnl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        pnl_menuLayout.setVerticalGroup(
            pnl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(pnl_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 170, 60));

        btn_adicionar.setBackground(new java.awt.Color(253, 253, 247));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 90, 30));

        btn_editar.setBackground(new java.awt.Color(253, 253, 247));
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 90, 30));

        jPanel2.setBackground(new java.awt.Color(16, 64, 88));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Painel de Administrador");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 80));

        btn_eliminar.setBackground(new java.awt.Color(253, 253, 247));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 90, 30));

        jButton1.setBackground(new java.awt.Color(253, 253, 247));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        try {
            editar();
        } catch (DadosNaoEncontrados ex) {
            Logger.getLogger(ListarUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        try {
            eliminar();
        } catch (DadosNaoEncontrados ex) {
            Logger.getLogger(ListarUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fechar();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_menu;
    public static javax.swing.JTable tbl_utilizadores;
    // End of variables declaration//GEN-END:variables
}
