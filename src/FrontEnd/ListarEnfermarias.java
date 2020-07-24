package FrontEnd;

import BackEnd.Administrador;
import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Sistema;
import javax.swing.table.AbstractTableModel;
import Serializacao.Serializacao;
import javax.swing.JOptionPane;

public class ListarEnfermarias extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private AbstractTableModel modeloTabela;
    private Serializacao bd;
    private Hospital hospital;
    
    
    public ListarEnfermarias(Sistema sistema, Serializacao bd, Hospital hospital) {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.hospital = hospital;
        this.modeloTabela = criarModeloTabela();
        tbl_enfermaria.setModel(modeloTabela);
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        //Administrador não consegue adicionar, editar ou eliminar enfermrias
        if (sistema.getUtilizadorLigado() instanceof Administrador) {
            btn_adicionar.setVisible(false);
            btn_editar.setVisible(false);
            btn_eliminar.setVisible(false);
        } else {
            btn_adicionar.setVisible(true);
            btn_editar.setVisible(true);
            btn_eliminar.setVisible(true);
        }
    }

    //Criar tabela abstrata
    private AbstractTableModel criarModeloTabela() {   
        String[] nomeColunas = {"Codigo", "Tipo","Nº Camas"};
        
        return new AbstractTableModel() {     
            @Override
            public String getColumnName(int column) {
                return nomeColunas[column];
            }
           
            @Override
            public int getRowCount() {
                //Retorna o número de linhas que a tabela deverá ter
                return hospital.getLEnfermarias().size();
            }

            @Override
            public int getColumnCount() {
                //Retorna o número de colunas que a tabela deverá ter
                return nomeColunas.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
            /*
                Este método é invocado quando se pretende "popular" cada uma das células da tabela
                Se a tabela tem 3 linhas e 2 colunas existem 6 células (3*2), logo o método será invocado 6 vezes
                    rowIndex representa a linha da célula (0 a rowCount -1)
                    columnIndex representa a coluna da célula (0 a ColumnCount -1)
            */
                switch (columnIndex) {
                    case 0: 
                        return hospital.getLEnfermarias().todos().get(rowIndex).getCodigo();
                    case 1:
                        return hospital.getLEnfermarias().todos().get(rowIndex).getTipo();
                    case 2:
                        return hospital.getLEnfermarias().todos().get(rowIndex).getNCamas();
                    default:
                        return "";
                }                              
            }            
        };    
    }
    
    
    //adicionar enfermaria
    private void adicionar() {
        DadosEnfermarias janela = new DadosEnfermarias(sistema, bd, null, hospital, this);   
        janela.setVisible(true);
    }
    
    //editar enfermaria
    private void editar() throws ListaEnfermarias.EnfermariaNaoExistenteException {
        int rowIndex = tbl_enfermaria.getSelectedRow();
        //Se nenhum registo selecionado, nao é possivel editar
        if (rowIndex == -1) return;
        
        String codigo = (String) modeloTabela.getValueAt(rowIndex, 0);
        
        Enfermaria enfermaria = hospital.getLEnfermarias().getEnfermaria(codigo);
        DadosEnfermarias janela = new DadosEnfermarias(sistema, bd, enfermaria, hospital, this);
        janela.setVisible(true);   
    }
    
    //eliminar enfermaria
    private void eliminar()  {
        if (tbl_enfermaria.getSelectedRow() != -1) {
                int h = tbl_enfermaria.getSelectedRow();
                String codigo = tbl_enfermaria.getValueAt(h, 0).toString();
 
                hospital.getLEnfermarias().remove(codigo);
                
                modeloTabela.fireTableRowsDeleted(h, 0);
                JOptionPane.showMessageDialog(this, "Enfermaria eliminado com sucesso.");
            }
    }
    
    public void atualizar() {                 
        modeloTabela.fireTableDataChanged();
    } 
    
    private void fechar() {
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_enfermaria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_adicionar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_enfermaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Tipo", "Nº Camas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_enfermaria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 165));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/enfermaria.png"))); // NOI18N
        jLabel1.setText("Enfermarias deste Hospital:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        btn_adicionar.setBackground(new java.awt.Color(253, 253, 247));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 90, 30));

        btn_editar.setBackground(new java.awt.Color(253, 253, 247));
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 90, 30));

        jPanel2.setBackground(new java.awt.Color(89, 136, 160));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Painel de Enfermarias");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 80));

        btn_fechar.setBackground(new java.awt.Color(253, 253, 247));
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        btn_fechar.setBorder(null);
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, -1, -1));

        btn_eliminar.setBackground(new java.awt.Color(253, 253, 247));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        try {
            editar();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        fechar();
    }//GEN-LAST:event_btn_fecharActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbl_enfermaria;
    // End of variables declaration//GEN-END:variables
}
