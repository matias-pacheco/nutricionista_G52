/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import nutricionista_g52.accesoADatos.DietaData;
import nutricionista_g52.accesoADatos.PacienteData;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.entidades.Historial_Peso;
import nutricionista_g52.entidades.Paciente;
import nutricionista_g52.vistas.EditarPacienteView;
import nutricionista_g52.vistas.MenuPrincipalView;
import nutricionista_g52.vistas.NuevoPacienteView;
import nutricionista_g52.vistas.PesoView;

/**
 *
 * @author Matías Pacheco
 */
public class ConsultasPacientesView extends javax.swing.JInternalFrame {
    private DefaultTableModel modeloTab;
    private DietaData dieData;
    private PacienteData pacData;
    
    /**
     * Creates new form PacientesView
     */
    public ConsultasPacientesView() {
        initComponents();
        this.modeloTab = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
        };
        this.dieData = new DietaData();
        this.pacData = new PacienteData();
        porDefecto();
    }
    
//---------- Por Defecto ----------
    private void porDefecto(){
        listarJComBoOrdenarPor();
        listarJComBoBuscarPorDieta();
        listarJComBoBuscarPorPeso();
        agregarColumnasALaTabla();
        deshabilitarElReordenamientoDeColumnas();
        habilitarComponentes(false);
        llenaTablaPorDefecto();
    }
    
    private void listarJComBoOrdenarPor(){
        jComBoOrdenarPor.addItem("POR APELLIDO");
        jComBoOrdenarPor.addItem("POR DNI");
        jComBoOrdenarPor.addItem("POR DIETA");
        jComBoOrdenarPor.addItem("POR FECHA");
    }
    
    private void listarJComBoBuscarPorDieta(){
        jComBoBuscarPorDieta.addItem("POR DIETA VIGENTE");
        jComBoBuscarPorDieta.addItem("POR DIETA TERMINADA");
    }
    
    private void listarJComBoBuscarPorPeso(){
        jComBoBuscarPorPeso.addItem("TODOS");
        jComBoBuscarPorPeso.addItem("POR PESO ALCANZADO");
        jComBoBuscarPorPeso.addItem("POR PESO NO ALCANZADO");
    }
    
    private void agregarColumnasALaTabla(){
        modeloTab.addColumn("DNI");
        modeloTab.addColumn("APELLIDO");
        modeloTab.addColumn("NOMBRE");
        modeloTab.addColumn("DIETA");
        modeloTab.addColumn("FINALIZA");
        modeloTab.addColumn("PESO BUSCADO");
        
        jTabConsultasPacientes.setModel(modeloTab);
    }
    
    private void deshabilitarElReordenamientoDeColumnas(){
        jTabConsultasPacientes.getTableHeader().setReorderingAllowed(false);
    }
    
    private void habilitarComponentes(boolean habilitado){
        jComBoBuscarPorPeso.setEnabled(habilitado);
    }
    
    private void llenaTablaPorDefecto(){
        vaciarFilasDeLaTabla();
        
        if(jComBoBuscarPorDieta.getSelectedIndex() == 0){
            llenarFilasDeLaTabla(dieData.listarPacientesDietaVigenteEstrictoPorOrdenAsc(ordenarFilasPor()));
        } else {
            buscarFilasPorPesoAlcanzado();
        }
    }
   
//---------- Listar, Elegir, Vaciar, Buscar ----------
    private void llenarFilasDeLaTabla(List<Object> registro){
        Object[] arreglo = null;
        
        for(Object obj : registro){
            if(obj instanceof Paciente){
                arreglo = new Object[6];
                Paciente paciente = (Paciente) obj;
                arreglo[0] = paciente.getDni(); arreglo[1] = paciente.getApellido(); arreglo[2] = paciente.getNombre();
            } else if(obj instanceof Dieta){
                Dieta dieta = (Dieta) obj;
                arreglo[3] = dieta.getNombre(); arreglo[4] = dieta.getFechaFinal(); arreglo[5] = dieta.getPesoFinal();
                modeloTab.addRow(arreglo);
            }
        }
    }
    
    private String ordenarFilasPor(){
        String ordenarPor = null;
        
        switch (jComBoOrdenarPor.getSelectedIndex()) {
            case 0:{ ordenarPor = "apellido"; break; }
            case 1:{ ordenarPor = "dni"; break; }
            case 2:{ ordenarPor = "dieta.nombre"; break; }
            case 3:{ ordenarPor = "fechaFinal"; break; }
        }
        
        return ordenarPor;
    }
    
    private void vaciarFilasDeLaTabla(){
        modeloTab.setRowCount(0);
    }
    
    private void buscarFilasPorPesoAlcanzado(){
        switch(jComBoBuscarPorPeso.getSelectedIndex()){
            case 0:{ llenarFilasDeLaTabla(dieData.listarPacientesDietaTerminadaEstrictoPorOrdenAsc(ordenarFilasPor())); break; }
            case 1:{ llenarFilasDeLaTabla(dieData.listarPacientesQueAlcanzaronPesoBuscadoPorOrdenAsc(ordenarFilasPor())); break; }
            case 2:{ llenarFilasDeLaTabla(dieData.listarPacientesQueNoAlcanzaronPesoBuscadoPorOrdenAsc(ordenarFilasPor())); break; }
        }
    }
    
    private int seleccionarDniEnTabla(){
        int dni = (int) jTabConsultasPacientes.getValueAt(jTabConsultasPacientes.getSelectedRow(), 0);
        return dni;
    }
    
    private Dieta seleccionarDietaEnTabla(){
        Dieta dieta = dieData.buscarDietaPorDni(seleccionarDniEnTabla());
        return dieta;
    }
    
    private Paciente seleccionarPacienteEnTabla(){
        Paciente paciente = pacData.buscarpacientePorDni(seleccionarDniEnTabla());
        return paciente;
    }
    
    private boolean dietaVigente(){
        return dieData.isPacienteHaceDieta(seleccionarDniEnTabla());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabConsultasPacientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButPeso = new javax.swing.JButton();
        jButVer = new javax.swing.JButton();
        jButSalir = new javax.swing.JButton();
        jButDieta = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jComBoBuscarPorDieta = new javax.swing.JComboBox<>();
        jComBoOrdenarPor = new javax.swing.JComboBox<>();
        jComBoBuscarPorPeso = new javax.swing.JComboBox<>();

        jTabConsultasPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(jTabConsultasPacientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jButPeso.setText("Peso");
        jButPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButPesoActionPerformed(evt);
            }
        });

        jButVer.setText("Ver");
        jButVer.setToolTipText("");
        jButVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButVerActionPerformed(evt);
            }
        });

        jButSalir.setText("Salir");
        jButSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButSalirActionPerformed(evt);
            }
        });

        jButDieta.setText("Dieta");
        jButDieta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButDietaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButVer, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButDieta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304)
                .addComponent(jButSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButPeso)
                    .addComponent(jButVer)
                    .addComponent(jButSalir)
                    .addComponent(jButDieta))
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultas PACIENTES");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("registro por selección");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12))
        );

        jComBoBuscarPorDieta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoBuscarPorDietaActionPerformed(evt);
            }
        });

        jComBoOrdenarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoOrdenarPorActionPerformed(evt);
            }
        });

        jComBoBuscarPorPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoBuscarPorPesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jComBoOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComBoBuscarPorDieta, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComBoBuscarPorPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComBoBuscarPorDieta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComBoOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComBoBuscarPorPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButPesoActionPerformed
        // TODO add your handling code here:
        
        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 2){
            if(jTabConsultasPacientes.getSelectedRow() == -1){
                try{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un paciente de la tabla", "  Mensaje", 1);
                    return;
                } catch(HeadlessException he){
                    System.err.println(he.getMessage());
                }
            }
            
            PesoView pesoV = new PesoView(seleccionarPacienteEnTabla(), dietaVigente());
            pesoV.setVisible(true);
            
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - pesoV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - pesoV.getHeight()) / 2;
        
            pesoV.setLocation(x, y);
            
            MenuPrincipalView.jDesPaEscritorio.add(pesoV);
            
            pesoV.toFront();
        }
    }//GEN-LAST:event_jButPesoActionPerformed

    private void jButDietaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButDietaActionPerformed
        // TODO add your handling code here:
        
        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 2){
            if(jTabConsultasPacientes.getSelectedRow() == -1){
                try{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una dieta de la tabla", "  Mensaje", 1);
                    return;
                } catch(HeadlessException he){
                    System.err.println(he.getMessage());
                }
            }
            
            DietaView dieV = new DietaView(seleccionarDietaEnTabla());
            dieV.setVisible(true);
            
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - dieV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - dieV.getHeight()) / 2;
        
            dieV.setLocation(x, y);
            
            MenuPrincipalView.jDesPaEscritorio.add(dieV);
            
            dieV.toFront();
        }
    }//GEN-LAST:event_jButDietaActionPerformed

    private void jButVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButVerActionPerformed
        // TODO add your handling code here:
        
        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 2){
            if(jTabConsultasPacientes.getSelectedRow() == -1){
                try{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un paciente de la tabla", "  Mensaje", 1);
                    return;
                } catch(HeadlessException he){
                    System.err.println(he.getMessage());
                }
            }
            
            VerPacienteView verPacV = new VerPacienteView(seleccionarPacienteEnTabla());
            verPacV.setVisible(true);
            
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - verPacV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - verPacV.getHeight()) / 2;
        
            verPacV.setLocation(x, y);
            
            MenuPrincipalView.jDesPaEscritorio.add(verPacV);
            
            verPacV.toFront();
        }
    }//GEN-LAST:event_jButVerActionPerformed

    private void jButSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButSalirActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_jButSalirActionPerformed

    private void jComBoOrdenarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoOrdenarPorActionPerformed
        // TODO add your handling code here:
        llenaTablaPorDefecto();
    }//GEN-LAST:event_jComBoOrdenarPorActionPerformed

    private void jComBoBuscarPorDietaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoBuscarPorDietaActionPerformed
        // TODO add your handling code here:
        llenaTablaPorDefecto();

        if(jComBoBuscarPorDieta.getSelectedIndex() == 1){
            habilitarComponentes(true);
        } else {
            habilitarComponentes(false);
        }
    }//GEN-LAST:event_jComBoBuscarPorDietaActionPerformed

    private void jComBoBuscarPorPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoBuscarPorPesoActionPerformed
        // TODO add your handling code here:
        llenaTablaPorDefecto();
    }//GEN-LAST:event_jComBoBuscarPorPesoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButDieta;
    private javax.swing.JButton jButPeso;
    private javax.swing.JButton jButSalir;
    private javax.swing.JButton jButVer;
    private javax.swing.JComboBox<String> jComBoBuscarPorDieta;
    private javax.swing.JComboBox<String> jComBoBuscarPorPeso;
    private javax.swing.JComboBox<String> jComBoOrdenarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabConsultasPacientes;
    // End of variables declaration//GEN-END:variables
}
