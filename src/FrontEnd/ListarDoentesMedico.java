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
import javax.swing.table.DefaultTableModel;

public class ListarDoentesMedico extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private DefaultTableModel modeloTabela;
    private ProfissionalSaude medico;

    public ListarDoentesMedico(Sistema sistema, Hospital hospital, Serializacao bd, ProfissionalSaude medico) throws DadosEmBranco, DadosInvalidos {
        initComponents();
        this.sistema = sistema;
        this.hospital = hospital;
        this.bd = bd;
        this.medico = medico;
        modeloTabela = (DefaultTableModel) tbl_doentes.getModel();
        tbl_doentes.setModel(modeloTabela);
        preencheTabela(); 
 
        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
 
    //para preencher a tabela
    public void preencheTabela() throws DadosEmBranco, DadosInvalidos {
        modeloTabela.setRowCount(0);
        hospital.getLDoentes().ordenarNAsc();
        for (Doente d : ((Medico)medico).getLDoentes().getLista()) {
    
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getEstado(), d.getNumCama()});
              
        }
    }
 
    //Adicionar um novo doente
    private void adicionar() throws ListaEnfermarias.EnfermariaNaoExistenteException   {
        DadosDoenteMedico janela = new DadosDoenteMedico(sistema, bd,hospital, medico, this);   
        janela.setVisible(true);
    }
 
    //Remover um doente
    private void eliminar() throws ListaEnfermarias.EnfermariaNaoExistenteException  {
        if (tbl_doentes.getSelectedRow() != -1) {
                int h = tbl_doentes.getSelectedRow();
                String codigo = tbl_doentes.getValueAt(h, 0).toString();
 
               //Apagar o doente na lista de medicos
               Doente doente = hospital.getLDoentes().ficha(codigo);
               ((Medico)medico).removerDoente(doente);
                
                modeloTabela.removeRow(h);
                guardarAlteracoes();
                JOptionPane.showMessageDialog(this, "Doente eliminado com sucesso.");
            }
    }
    
    public DefaultTableModel getModeloTab() {
        return modeloTabela;
    } 
    
    //Atualizar a tabela
    public void atualizar() {                 
        modeloTabela.fireTableDataChanged();
    } 
    
    public void guardarAlteracoes() {
        bd.guardar(sistema);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_doentes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_adicionar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_doentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Localidade", "Enfermaria", "Nº Cama", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_doentes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 720, 200));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/enfermaria.png"))); // NOI18N
        jLabel1.setText("Doentes do médico:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        btn_adicionar.setBackground(new java.awt.Color(253, 253, 247));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 90, 30));

        jPanel2.setBackground(new java.awt.Color(89, 136, 160));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista de Doentes do Médico");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(406, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 80));

        btn_fechar.setBackground(new java.awt.Color(253, 253, 247));
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        btn_fechar.setBorder(null);
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, -1, -1));

        btn_eliminar.setBackground(new java.awt.Color(253, 253, 247));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        try {
            adicionar();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(ListarDoentesMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        try {
            eliminar();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(ListarDoentesMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbl_doentes;
    // End of variables declaration//GEN-END:variables
}
