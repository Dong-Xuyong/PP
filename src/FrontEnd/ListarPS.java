package FrontEnd;

import BackEnd.Administrador;
import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Medico;
import BackEnd.ProfissionalSaude;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListarPS extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private DefaultTableModel modeloTabela;


    public ListarPS(Sistema sistema, Hospital hospital, Serializacao bd) throws DadosEmBranco, DadosInvalidos {
        initComponents();
        this.sistema = sistema;
        this.hospital = hospital;
        this.bd = bd;
        modeloTabela = (DefaultTableModel) tbl_ps.getModel();
        tbl_ps.setModel(modeloTabela);
        preencheTabela();

         //Não permite o redimensionamento da janela
        this.setResizable(false);

        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);

        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        //Administrador não consegue adicionar, editar ou eliminar profissionais de saúde
        if (sistema.getUtilizadorLigado() instanceof Administrador) {
            btn_adicionar.setVisible(false);
            btn_editar.setVisible(false);
            btn_eliminar.setVisible(false);
        } else {
            btn_adicionar.setVisible(true);
            btn_editar.setVisible(true);
            btn_eliminar.setVisible(true);
        }
   
        //Encher a comboBox filtrar enfermaria
        ListaEnfermarias lista = hospital.getLEnfermarias();
        for(Enfermaria e: lista.todos()) {
            cb_filtrarEnfermaria.addItem(e.getCodigo());
        }
    }
    
    //Preencher a tabela
    public void preencheTabela() throws DadosEmBranco, DadosInvalidos {
        modeloTabela.setRowCount(0);
        hospital.getLPS().ordenarNAsc();
          for (ProfissionalSaude d : hospital.getLPS().getLista()) {
              if(d.getEnfermaria() != null) {
                if (d instanceof Medico) {
                    modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getEnfermaria(), d.getCargo(), ((Medico) d).getEspecialidade()});
                } else
                    modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getEnfermaria(), d.getCargo(), ""});
            }
        }
    }
    
    //Adicionar um profissional de saude
    private void adicionar() {
        DadosPS janela = new DadosPS(sistema, bd, hospital, null , null, this);   
        janela.setVisible(true);
    }
    
    //Editar profissional de saude
    private void editar()  {
        int rowIndex = tbl_ps.getSelectedRow();
        //Se nenhum registo selecionado, nao é possivel editar
        if (rowIndex == -1) return;
        
        String codigo = (String) modeloTabela.getValueAt(rowIndex, 0);
        
        ProfissionalSaude ps  = hospital.getLPS().getPs(codigo);
        DadosPS janela = new DadosPS(sistema, bd, hospital, ps, null, this);
        janela.setVisible(true);   
    }
    
    //Eliminar profissional de saude
    private void eliminar()  {
        if (tbl_ps.getSelectedRow() != -1) {
                int p = tbl_ps.getSelectedRow();
                String codigo = tbl_ps.getValueAt(p, 0).toString();
 
                hospital.getLPS().remover(codigo);
                
                modeloTabela.removeRow(p);
                JOptionPane.showMessageDialog(this, "Profissional eliminado com sucesso.");
            }
    }
       
    public DefaultTableModel getModeloTab() {
      return modeloTabela;
    }
       
    //Filtrar a lista por enfermaria
    private void filtrarEnfermaria() {
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }

        ArrayList<Enfermaria> filtrarEnf = new ArrayList();
        String cod_enfermaria = cb_filtrarEnfermaria.getSelectedItem().toString();

            if(!cod_enfermaria.equals("Selecione uma opcao:")) {
            filtrarEnf.addAll(hospital.getLEnfermarias().filtrarEnfermaria(cod_enfermaria));
            } 

        for (Enfermaria e : filtrarEnf) {
            for (ProfissionalSaude m : hospital.getLPS().lista ) {
               if (m.getEnfermaria().equals(e.getCodigo())) {
                   if (m instanceof Medico) {
                    modeloTabela.addRow(new Object[]{m.getCodigo(), m.getNome(), m.getEnfermaria(), m.getCargo(), ((Medico) m).getEspecialidade()});
                } else
                    modeloTabela.addRow(new Object[]{m.getCodigo(), m.getNome(), m.getEnfermaria(), m.getCargo(), ""});
            }
               } 
        }

    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ps = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_adicionar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        cb_filtrarEnfermaria = new javax.swing.JComboBox<>();
        lbl_listarDoentes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_ps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Enfermaria", "Cargo", "Especialidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_ps);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 165));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setText("Profissionais de Saúde do Hospital:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        btn_adicionar.setBackground(new java.awt.Color(253, 253, 247));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 90, 30));

        btn_editar.setBackground(new java.awt.Color(253, 253, 247));
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 90, 30));

        jPanel2.setBackground(new java.awt.Color(89, 136, 160));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Profissionais de Saúde");

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
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 90, 30));

        cb_filtrarEnfermaria.setBackground(new java.awt.Color(253, 253, 247));
        cb_filtrarEnfermaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opcao:" }));
        cb_filtrarEnfermaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_filtrarEnfermariaActionPerformed(evt);
            }
        });
        jPanel1.add(cb_filtrarEnfermaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 150, 30));

        lbl_listarDoentes.setBackground(new java.awt.Color(253, 253, 247));
        lbl_listarDoentes.setText("Listar Doentes");
        lbl_listarDoentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_listarDoentesActionPerformed(evt);
            }
        });
        jPanel1.add(lbl_listarDoentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        editar();
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void cb_filtrarEnfermariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_filtrarEnfermariaActionPerformed
        filtrarEnfermaria();
    }//GEN-LAST:event_cb_filtrarEnfermariaActionPerformed

    private void lbl_listarDoentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_listarDoentesActionPerformed
        if(tbl_ps.getSelectedRow() != -1){
            String codigo = tbl_ps.getValueAt(tbl_ps.getSelectedRow(), 0).toString();
            try {
                ProfissionalSaude ps = hospital.getLPS().getPs(codigo);
                
                if(ps.getCargo().equals("Medico")) {
                    ListarDoentesMedico lista = new ListarDoentesMedico(sistema, hospital, bd, ps);
                    lista.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Enfermeiro não tem acesso à lista de doentes!");
                }
            } catch (DadosEmBranco | DadosInvalidos ex) {
                Logger.getLogger(ListarPS.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else
           JOptionPane.showMessageDialog(this, "Linha não selecionada!");
    }//GEN-LAST:event_lbl_listarDoentesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JComboBox<String> cb_filtrarEnfermaria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lbl_listarDoentes;
    public static javax.swing.JTable tbl_ps;
    // End of variables declaration//GEN-END:variables

    
}
