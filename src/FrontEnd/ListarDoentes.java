package FrontEnd;

import BackEnd.Administrador;
import BackEnd.Doente;
import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import excetions.DadosEmBranco;
import excetions.DadosInvalidos;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListarDoentes extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private DefaultTableModel modeloTabela;
    private Doente doente = null;
    

    public ListarDoentes(Sistema sistema, Hospital hospital, Serializacao bd) throws DadosEmBranco, DadosInvalidos {
        initComponents();
        this.sistema = sistema;
        this.hospital = hospital;
        this.bd = bd;
        modeloTabela = (DefaultTableModel) tbl_doentes.getModel();
        tbl_doentes.setModel(modeloTabela);
        preencheTabela();

        //Não permite o redimensionamento da janela
        this.setResizable(false);
        
        //Mostra a centralização da janela
        this.setLocationRelativeTo(null);
        
        //O processo de fecho da janela será controlado pelo programa
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        //Administrador não consegue adicionar, editar, eliminar, nem dar alta aos doentes.
        if (sistema.getUtilizadorLigado() instanceof Administrador) {
            btn_adicionar.setVisible(false);
            btn_editar.setVisible(false);
            btn_eliminar.setVisible(false);
            btn_darAlta.setVisible(false);
        } else {
            btn_adicionar.setVisible(true);
            btn_editar.setVisible(true);
            btn_eliminar.setVisible(true);
            btn_darAlta.setVisible(true);
        }
    }
 
    public void preencheTabela() throws DadosEmBranco, DadosInvalidos {
        modeloTabela.setRowCount(0);
        hospital.getLDoentes().ordenarNAsc();
        for (Doente d : hospital.getLDoentes().getLista()) {
            if (d.getEnfermaria() != null) {
                if (d.getDataSaida() != null) {
                    modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
                } else {
                    modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), ""});
                }
            } else {
                if (d.getDataSaida() != null) {
                    modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), "", d.getEstado(), d.getNumCama(), d.getDataEntrada(), d.getDataSaida()});
                } else {
                    modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), "", d.getEstado(), d.getNumCama(), d.getDataEntrada(), ""});
                }
            }
        }
    }

    //adicionar um doente
    private void adicionar() throws ListaEnfermarias.EnfermariaNaoExistenteException  {
        DadosDoente janela = new DadosDoente(sistema, bd, doente, hospital, this);   
        janela.setVisible(true);
    }
    
    //editar um doente
    private void editar() throws ListaEnfermarias.EnfermariaNaoExistenteException  {
        int rowIndex = tbl_doentes.getSelectedRow();
        //Se nenhum registo selecionado, nao é possivel editar
        if (rowIndex == -1) return;
        
        String codigo = (String) modeloTabela.getValueAt(rowIndex, 0);
        
        Doente doente = hospital.getLDoentes().getDoente(codigo);
        DadosDoente janela = new DadosDoente(sistema, bd, doente, hospital, this);
        janela.setVisible(true);   
        
    }
    
    //eliminar um doente
    private void eliminar() throws ListaEnfermarias.EnfermariaNaoExistenteException  {
        if (tbl_doentes.getSelectedRow() != -1) {
                int h = tbl_doentes.getSelectedRow();
                String codigo = tbl_doentes.getValueAt(h, 0).toString();
                String cod_enfermaria = tbl_doentes.getValueAt(h, 3).toString();
 
            //Obter a enfermaria do doente e habilitar a cama do doente nessa enfermaria
            Enfermaria enfermaria = hospital.getLEnfermarias().getEnfermaria(cod_enfermaria);
            Doente doente = hospital.getLDoentes().getDoente(codigo);
            
            enfermaria.getLCamas().getCama(doente.getNumCama()).habilitar();
                
            hospital.getLDoentes().apagar(codigo);
                
            
            
                modeloTabela.removeRow(h);
                JOptionPane.showMessageDialog(this, "Doente eliminado com sucesso.");
            }
    }
    
    public DefaultTableModel getModeloTab() {
        return modeloTabela;
    }
    
    //dar alta a um doente 
    private void darAlta() throws DadosEmBranco, DadosInvalidos, ListaEnfermarias.EnfermariaNaoExistenteException {
        if (tbl_doentes.getSelectedRow() != -1) {
            
            int d = tbl_doentes.getSelectedRow();
            String cod = tbl_doentes.getValueAt(d, 0).toString();
            String cod_enfermaria = tbl_doentes.getValueAt(d, 3).toString();
            
            System.out.println(cod+cod_enfermaria);
            //Obter a enfermaria do doente e habilitar a cama do doente nessa enfermaria
            Enfermaria enfermaria = hospital.getLEnfermarias().getEnfermaria(cod_enfermaria);
            Doente doente = hospital.getLDoentes().getDoente(cod);
            
            enfermaria.getLCamas().getCama(doente.getNumCama()).habilitar();
            hospital.getLDoentes().getDoente(cod).alta();
            
            preencheTabela();
            JOptionPane.showMessageDialog(this, "Doente recebeu alta");
        }
    }
    
    public void atualizar() {                 
        modeloTabela.fireTableDataChanged();
    } 
    
    public void guardarAlteracoes() {
        bd.guardar(sistema);
    }
    
    private void filtrarLista() {
        FiltrarListaDoentes lista = new FiltrarListaDoentes (sistema, bd, hospital, this);
        lista.setVisible(true);
    }
    
    //ordena a lista pelo estado
    private void ordenarEstado() {
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        ArrayList<Doente> ordenar = new ArrayList();
        // Filtrar por enfermaria
            ordenar = (ArrayList) hospital.getLDoentes().ordenarEstado();
 
    for(Doente d: ordenar){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }
    
    //ordena a lista por data de entrada
    private void ordenarDataEntrada() {
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        ArrayList<Doente> ordenar = new ArrayList();
        // Filtrar por enfermaria
       
            ordenar.addAll(hospital.getLDoentes().ordenarDataA());
 
    for(Doente d: ordenar){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }
    
    //ordena a lista pela ordem do alfabeto
    private void ordenarAlfabeto() {
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        ArrayList<Doente> ordenar = new ArrayList();
        // Filtrar por enfermaria
        
            ordenar.addAll(hospital.getLDoentes().ordenarNAsc());
   
    for(Doente d: ordenar){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_doentes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_adicionar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_darAlta = new javax.swing.JButton();
        btn_filtrarLista = new javax.swing.JButton();
        cb_ordenar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_doentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Localidade", "Enfermaria", "Nº Cama", "Estado", "Data Entrada", "Data Saída"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_doentes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 720, 200));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/enfermaria.png"))); // NOI18N
        jLabel1.setText("Doentes deste Hospital:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        btn_adicionar.setBackground(new java.awt.Color(253, 253, 247));
        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 90, 30));

        btn_editar.setBackground(new java.awt.Color(253, 253, 247));
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 90, 30));

        jPanel2.setBackground(new java.awt.Color(89, 136, 160));

        jLabel2.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Painel de Doentes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(520, Short.MAX_VALUE))
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
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 380, -1, -1));

        btn_eliminar.setBackground(new java.awt.Color(253, 253, 247));
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, 90, 30));

        btn_darAlta.setBackground(new java.awt.Color(253, 253, 247));
        btn_darAlta.setText("Dar Alta");
        btn_darAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_darAltaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_darAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 90, 30));

        btn_filtrarLista.setBackground(new java.awt.Color(253, 253, 247));
        btn_filtrarLista.setText("Filtrar");
        btn_filtrarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrarListaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_filtrarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 90, 30));

        cb_ordenar.setBackground(new java.awt.Color(253, 253, 247));
        cb_ordenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ordenar por:", "Alfabeto", "Data Entrada", "Gravidade", " " }));
        cb_ordenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ordenarActionPerformed(evt);
            }
        });
        jPanel1.add(cb_ordenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 120, 30));

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
            Logger.getLogger(ListarDoentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        try {
            editar();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(ListarDoentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        try {
            eliminar();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(ListarDoentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_darAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_darAltaActionPerformed
        try {
            darAlta();
        } catch (DadosEmBranco | DadosInvalidos | ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(ListarDoentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        guardarAlteracoes();
    }//GEN-LAST:event_btn_darAltaActionPerformed

    private void btn_filtrarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarListaActionPerformed
        filtrarLista();
    }//GEN-LAST:event_btn_filtrarListaActionPerformed

    private void cb_ordenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ordenarActionPerformed
        String tipo = cb_ordenar.getSelectedItem().toString();
        if(tipo.equals("Alfabeto"))
            ordenarAlfabeto();
        else
            if(tipo.equals("Data Entrada"))
                ordenarDataEntrada();
                    else
                        ordenarEstado();
    }//GEN-LAST:event_cb_ordenarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_darAlta;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JButton btn_filtrarLista;
    private javax.swing.JComboBox<String> cb_ordenar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbl_doentes;
    // End of variables declaration//GEN-END:variables
}
