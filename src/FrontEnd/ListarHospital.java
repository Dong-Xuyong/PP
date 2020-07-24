package FrontEnd;

import BackEnd.Administrador;
import BackEnd.Sistema;
import BackEnd.Hospital;
import javax.swing.table.AbstractTableModel;
import BackEnd.ListaHospitais;
import Serializacao.Serializacao;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ListarHospital extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private AbstractTableModel modeloTabela;
    
  
    public ListarHospital(Sistema sistema, Serializacao bd) {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.modeloTabela = criarModeloTabela();
        tbl_hospitais.setModel(modeloTabela);
        
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        //Administrador não consegue adicionar, editar ou eliminar hospitais
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
    
    //Construir tabela abstrata 
    private AbstractTableModel criarModeloTabela() {   
        String[] nomeColunas = {"Codigo", "Nome","Localidade"};
        
        return new AbstractTableModel() {     
            @Override
            public String getColumnName(int column) {
                return nomeColunas[column];
            }
           
            @Override
            public int getRowCount() {
                //Retorna o número de linhas que a tabela deverá ter
                return sistema.getHospitais().size();
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
                        return sistema.getHospitais().todos().get(rowIndex).getCodigo();
                    case 1:
                        return sistema.getHospitais().todos().get(rowIndex).getNome();
                    case 2:
                        return sistema.getHospitais().todos().get(rowIndex).getLocalidade();
                    default:
                        return "";
                }                              
            }            
        };    
    }
    
    //Adicionar um hospital
    private void adicionar() {
        DadosHospital janela = new DadosHospital(sistema, bd, null, this);   
        janela.setVisible(true);
    }
    
    //Editar hospital
    private void editar() throws ListaHospitais.HospitalNaoExistenteException {
        int rowIndex = tbl_hospitais.getSelectedRow();
        //Se nenhum registo selecionado, nao é possivel editar
        if (rowIndex == -1) return;
        
        String codigo = (String) modeloTabela.getValueAt(rowIndex, 0);
        
        Hospital hospital = sistema.getHospitais().getHospital(codigo);
        DadosHospital janela = new DadosHospital(sistema, bd, hospital, this);
        janela.setVisible(true);  
    }
    
    //Eliminar hospital
    private void eliminar()  {
        if (tbl_hospitais.getSelectedRow() != -1) {
                int h = tbl_hospitais.getSelectedRow();
                String codigo = tbl_hospitais.getValueAt(h, 0).toString();
 
                sistema.getHospitais().remove(codigo);
           
                modeloTabela.fireTableRowsDeleted(h, 0);
                JOptionPane.showMessageDialog(this, "Hospital eliminado com sucesso.");
            }
    }
    
    //Atualizar tabela
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
        tbl_hospitais = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_adicionar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_doentes = new javax.swing.JButton();
        btn_fechar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_enfermarias = new javax.swing.JButton();
        btn_ps = new javax.swing.JButton();
        btn_equipamentos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_hospitais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Localidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_hospitais);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 165));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/hospital2.png"))); // NOI18N
        jLabel1.setText("Hospitais Registados no Sistema:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        btn_adicionar.setBackground(new java.awt.Color(253, 253, 247));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 90, 30));

        btn_editar.setBackground(new java.awt.Color(253, 253, 247));
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 90, 30));

        jPanel2.setBackground(new java.awt.Color(16, 64, 88));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Painel de Hospitais");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(402, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 80));

        btn_doentes.setBackground(new java.awt.Color(185, 203, 203));
        btn_doentes.setText("Doentes");
        btn_doentes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_doentes.setMaximumSize(new java.awt.Dimension(79, 23));
        btn_doentes.setMinimumSize(new java.awt.Dimension(79, 23));
        btn_doentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doentesActionPerformed(evt);
            }
        });
        jPanel1.add(btn_doentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 170, 35));

        btn_fechar.setBackground(new java.awt.Color(253, 253, 247));
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        btn_fechar.setBorder(null);
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, -1, -1));

        btn_eliminar.setBackground(new java.awt.Color(253, 253, 247));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 90, 30));

        btn_enfermarias.setBackground(new java.awt.Color(185, 203, 203));
        btn_enfermarias.setText("Enfermarias");
        btn_enfermarias.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_enfermarias.setMaximumSize(new java.awt.Dimension(79, 23));
        btn_enfermarias.setMinimumSize(new java.awt.Dimension(79, 23));
        btn_enfermarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enfermariasActionPerformed(evt);
            }
        });
        jPanel1.add(btn_enfermarias, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 170, 35));

        btn_ps.setBackground(new java.awt.Color(185, 203, 203));
        btn_ps.setText(" Profissionais de saúde");
        btn_ps.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_ps.setMaximumSize(new java.awt.Dimension(79, 23));
        btn_ps.setMinimumSize(new java.awt.Dimension(79, 23));
        btn_ps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_psActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ps, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 170, 35));

        btn_equipamentos.setBackground(new java.awt.Color(185, 203, 203));
        btn_equipamentos.setText(" Equipamentos");
        btn_equipamentos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_equipamentos.setMaximumSize(new java.awt.Dimension(79, 23));
        btn_equipamentos.setMinimumSize(new java.awt.Dimension(79, 23));
        btn_equipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_equipamentosActionPerformed(evt);
            }
        });
        jPanel1.add(btn_equipamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 170, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        try {
            editar();
        } catch (ListaHospitais.HospitalNaoExistenteException ex) {
            Logger.getLogger(ListarHospital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_doentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doentesActionPerformed
    if(tbl_hospitais.getSelectedRow() != -1){
            String codigo = tbl_hospitais.getValueAt(tbl_hospitais.getSelectedRow(), 0).toString();
            try {
                Hospital h = sistema.getHospitais().getHospital(codigo);
                ListarDoentes lista = new ListarDoentes(sistema, h, bd);
                lista.setVisible(true);
            } catch (ListaHospitais.HospitalNaoExistenteException | DadosEmBranco | DadosInvalidos ex) {
                Logger.getLogger(ListarHospital.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else
           JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_btn_doentesActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        fechar();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_enfermariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enfermariasActionPerformed
       if(tbl_hospitais.getSelectedRow() != -1){
            String codigo = tbl_hospitais.getValueAt(tbl_hospitais.getSelectedRow(), 0).toString();
            try {
                Hospital h = sistema.getHospitais().getHospital(codigo);
                ListarEnfermarias listenf = new ListarEnfermarias(sistema, bd, h);
                listenf.setVisible(true);
            } catch (ListaHospitais.HospitalNaoExistenteException ex) {
                Logger.getLogger(ListarHospital.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else
           JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_btn_enfermariasActionPerformed

    private void btn_psActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_psActionPerformed
        if(tbl_hospitais.getSelectedRow() != -1){
            String codigo = tbl_hospitais.getValueAt(tbl_hospitais.getSelectedRow(), 0).toString();
            try {
                Hospital h = sistema.getHospitais().getHospital(codigo);
                ListarPS listps = new ListarPS(sistema, h, bd);
                listps.setVisible(true);
            } catch (ListaHospitais.HospitalNaoExistenteException | DadosEmBranco | DadosInvalidos ex) {
                Logger.getLogger(ListarHospital.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else
           JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_btn_psActionPerformed

    private void btn_equipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_equipamentosActionPerformed
        if(tbl_hospitais.getSelectedRow() != -1){
            String codigo = tbl_hospitais.getValueAt(tbl_hospitais.getSelectedRow(), 0).toString();
            try {
                Hospital h = sistema.getHospitais().getHospital(codigo);
                ListarEquipamentos listar = new ListarEquipamentos(sistema, bd, h);
                listar.setVisible(true);
            } catch (ListaHospitais.HospitalNaoExistenteException ex) {
                Logger.getLogger(ListarHospital.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else
           JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_btn_equipamentosActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_doentes;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_enfermarias;
    private javax.swing.JButton btn_equipamentos;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JButton btn_ps;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbl_hospitais;
    // End of variables declaration//GEN-END:variables

 
}
