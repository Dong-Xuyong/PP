package FrontEnd;

import BackEnd.Doente;
import BackEnd.Enfermaria;
import BackEnd.Hospital;
import BackEnd.ListaEnfermarias;
import BackEnd.Sistema;
import Serializacao.Serializacao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class FiltrarListaDoentes extends javax.swing.JFrame {
    
    //Variaveis de instancia
    private Sistema sistema;
    private Serializacao bd;
    private Hospital hospital;
    private ListarDoentes listagem;
    private DefaultTableModel modeloTabela;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
    

    public FiltrarListaDoentes(Sistema sistema, Serializacao bd, Hospital hospital, ListarDoentes listagem) {
        initComponents();
        this.sistema = sistema;
        this.bd = bd;
        this.hospital = hospital;
        this.listagem = listagem;
        this.modeloTabela = listagem.getModeloTab();
        
        
        //Encher a ComboBox de enfermarias
        
        for (int i=0; i<hospital.getLEnfermarias().size(); i++) {
            String item = hospital.getLEnfermarias().todos().get(i).getCodigo();
            cb_enfermaria.addItem(item);
        }
        
        //Encher a ComboBox de localidades
        ArrayList<String> localidades = new ArrayList<String>();
        for (int i=0; i<hospital.getLDoentes().getLista().size(); i++) {
            localidades.add(hospital.getLDoentes().getLista().get(i).getLocalidade());
        }
        ArrayList<String> novasLocalidades = removerduplicado(localidades);
        for (int i=0; i<novasLocalidades.size(); i++) {
            cb_localidade.addItem(novasLocalidades.get(i));
        }
        
        
        //Encher a ComboBox de estados 
        ArrayList<String> estados = new ArrayList<String>();
        for (int i=0; i<hospital.getLDoentes().getLista().size(); i++) {
            estados.add(hospital.getLDoentes().getLista().get(i).getEstado());
        }
        ArrayList<String> novoEstado = removerduplicado(estados);
        for (int i=0; i<novoEstado.size(); i++) {
            cb_estado.addItem(novoEstado.get(i));
        }
        

       //Encher a ComboBox com datas de entrada
        ArrayList<String> entrada = new ArrayList<String>();
        for (int i=0; i<hospital.getLDoentes().getLista().size(); i++) {
            entrada.add(hospital.getLDoentes().getLista().get(i).getDataEntrada().toString());
        }
        ArrayList<String> novaEntrada = removerduplicado(entrada);
        for (int i=0; i<novaEntrada.size(); i++) {
            cb_dataEntrada.addItem(novaEntrada.get(i).toString());
        }
          
    }
    
    //Remove os duplicados 
    public ArrayList<String> removerduplicado(ArrayList<String> lista) {
        ArrayList<String> newList = new ArrayList<String>(); 
  
        for (String s : lista) { 
  
            if (!newList.contains(s)) { 
  
                newList.add(s); 
            } 
        } 
  
        return newList; 
        
    }
    
    
    private void filtrarEstado() {
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        
        ArrayList<Doente> filtrarEst = new ArrayList();
        
        // Filtrar por estado
        String estado = cb_estado.getSelectedItem().toString();
        if(!estado.equals("Selecione uma opcao")) {
        filtrarEst.addAll(hospital.getLDoentes().filtrarEstado(estado)) ;
        }
       
        for(Doente d: filtrarEst){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }
    
    private void filtrarLocalidade(){
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        
        ArrayList<Doente> filtrarLoc = new ArrayList();
        
        // Filtrar por localidade
         String localidade = cb_localidade.getSelectedItem().toString();
         if(!localidade.equals("Selecione uma opcao")) {
            filtrarLoc.addAll(hospital.getLDoentes().filtrarLocalidade(localidade));
         }
         
         for(Doente d: filtrarLoc){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }
    
    public void filtrarDataEntrada(){
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        
        ArrayList<Doente> filtrarDat = new ArrayList();
        
        //Filtrar por Data de entrada
        String date = cb_dataEntrada.getSelectedItem().toString();
        if(!date.equals("Selecione uma opcao")) {
        LocalDate data = LocalDate.parse(date);
        filtrarDat.addAll(hospital.getLDoentes().filtrarDEntrada(data));
        }
        
        for(Doente d: filtrarDat){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }

    private void filtrarEnfermaria() throws ListaEnfermarias.EnfermariaNaoExistenteException {
        
        for (int i = modeloTabela.getRowCount() - 1; i >= 0; i--) {
                modeloTabela.removeRow(i);
            }
        ArrayList<Doente> filtrarEnf = new ArrayList();
        // Filtrar por enfermaria
        
        String codigoSelecionado = cb_enfermaria.getSelectedItem().toString();
        if(!codigoSelecionado.equals("Selecione uma opcao")) {
            Enfermaria enfermaria = hospital.getLEnfermarias().getEnfermaria(codigoSelecionado);
            filtrarEnf.addAll(hospital.getLDoentes().filtrarEnfermaria(enfermaria));
        }
        
    for(Doente d: filtrarEnf){
            modeloTabela.addRow(new Object[]{d.getCodigo(), d.getNome(), d.getLocalidade(), d.getEnfermaria(), d.getNumCama(), d.getEstado(), d.getDataEntrada(), d.getDataSaida()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cb_estado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cb_dataEntrada = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb_enfermaria = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb_localidade = new javax.swing.JComboBox<>();
        btn_fechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(253, 253, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(89, 136, 160));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Filtrar Lista de Doentes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Enfermaria:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        cb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opcao" }));
        cb_estado.setToolTipText("");
        cb_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_estadoActionPerformed(evt);
            }
        });
        jPanel1.add(cb_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 137, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Estado:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 10));

        cb_dataEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opcao" }));
        cb_dataEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_dataEntradaActionPerformed(evt);
            }
        });
        jPanel1.add(cb_dataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 137, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Data entrada:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        cb_enfermaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opcao" }));
        cb_enfermaria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_enfermariaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cb_enfermariaMouseEntered(evt);
            }
        });
        cb_enfermaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_enfermariaActionPerformed(evt);
            }
        });
        jPanel1.add(cb_enfermaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 118, 137, 28));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Localidade:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        cb_localidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opcao" }));
        cb_localidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_localidadeActionPerformed(evt);
            }
        });
        jPanel1.add(cb_localidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 137, 28));

        btn_fechar.setBackground(new java.awt.Color(253, 253, 247));
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/recuar.png"))); // NOI18N
        btn_fechar.setBorder(null);
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void cb_enfermariaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_enfermariaMouseClicked
   
    }//GEN-LAST:event_cb_enfermariaMouseClicked

    private void cb_enfermariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_enfermariaActionPerformed
        try {
            filtrarEnfermaria();
        } catch (ListaEnfermarias.EnfermariaNaoExistenteException ex) {
            Logger.getLogger(FiltrarListaDoentes.class.getName()).log(Level.SEVERE, null, ex);
        }
        listagem.atualizar();
    }//GEN-LAST:event_cb_enfermariaActionPerformed

    private void cb_enfermariaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_enfermariaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_enfermariaMouseEntered

    private void cb_localidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_localidadeActionPerformed
        filtrarLocalidade();
        listagem.atualizar();
    }//GEN-LAST:event_cb_localidadeActionPerformed

    private void cb_dataEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_dataEntradaActionPerformed
        filtrarDataEntrada();
        listagem.atualizar();
    }//GEN-LAST:event_cb_dataEntradaActionPerformed

    private void cb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_estadoActionPerformed
        filtrarEstado();
        listagem.atualizar();
    }//GEN-LAST:event_cb_estadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_fechar;
    private javax.swing.JComboBox<String> cb_dataEntrada;
    private javax.swing.JComboBox<String> cb_enfermaria;
    private javax.swing.JComboBox<String> cb_estado;
    private javax.swing.JComboBox<String> cb_localidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
