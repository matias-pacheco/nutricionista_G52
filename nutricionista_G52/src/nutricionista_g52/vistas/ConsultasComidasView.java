/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import java.util.Collections;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import nutricionista_g52.accesoADatos.ComidaData;
import nutricionista_g52.entidades.Comida;
import nutricionista_g52.vistas.EditarPacienteView;
import nutricionista_g52.vistas.MenuPrincipalView;
import nutricionista_g52.vistas.NuevoPacienteView;
import nutricionista_g52.vistas.PesoView;
import nutricionista_g52.vistas.comparator.Comparadores;
import nutricionista_g52.vistas.excepciones.CampoVacioException;
import nutricionista_g52.vistas.excepciones.RangoNumericoException;

/**
 *
 * @author Matías Pacheco
 */
public class ConsultasComidasView extends javax.swing.JInternalFrame {
    private DefaultTableModel modeloTab;
    private ComidaData comiData;
    
    /**
     * Creates new form PacientesView
     */
    public ConsultasComidasView() {
        initComponents();
        this.modeloTab = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
        };
        this.comiData = new ComidaData();
        porDefecto();
    }
    
//---------- Por Defecto ----------
    private void porDefecto(){
        listarJComBoOrdenarPor();
        listarJComBoBuscarPorCalorias();
        agregarColumnasALaTabla();
        deshabilitarElReordenamientoDeColumnas();
        habilitarComponentes(false);
        llenarTablaPorDefecto();
    }
    
    private void listarJComBoOrdenarPor(){
        jComBoOrdenarPor.addItem("POR NOMBRE");
        jComBoOrdenarPor.addItem("POR CÓDIGO");
        jComBoOrdenarPor.addItem("POR CALORÍAS");
    }
    
    private void listarJComBoBuscarPorCalorias(){
        jComBoBuscarPorCalorias.addItem("POR APROXIMACIÓN DE CALORÍAS");
        jComBoBuscarPorCalorias.addItem("POR MAYOR CANTIDAD DE CALORÍAS");
        jComBoBuscarPorCalorias.addItem("POR MENOR CANTIDAD DE CALORÍAS");
    }
    
    private void agregarColumnasALaTabla(){
        modeloTab.addColumn("CÓDIGO");
        modeloTab.addColumn("COMIDA");
        modeloTab.addColumn("DETALLE");
        modeloTab.addColumn("CALORÍAS");
        
        jTabConsultasComidas.setModel(modeloTab);
    }
    
    private void deshabilitarElReordenamientoDeColumnas(){
        jTabConsultasComidas.getTableHeader().setReorderingAllowed(false);
    }
    
    private void habilitarComponentes(boolean habilitado){
        jButAgregar.setEnabled(habilitado);
    }
    
    private void llenarTablaPorDefecto(){
        vaciarFilasDeLaTabla();
        llenarFilasDeLaTabla(comiData.listarComidas());
    }
    
    private void llenarTablaPersonalizado(String calorias){
        vaciarFilasDeLaTabla();
        buscarComidasPorCalorias(calorias);
    }
    
//---------- Listar, Ordenar, Vaciar ----------
    private void llenarFilasDeLaTabla(List<Comida> registro){
        for(Comida comida : ordenarFilasPor(registro)){
            modeloTab.addRow(new Object[] {comida.getIdComida(), comida.getNombre(), comida.getDetalle(), comida.getCantCalorias()});
        }
    }
    
    private List<Comida> ordenarFilasPor(List<Comida> registro){
        switch(jComBoOrdenarPor.getSelectedIndex()){
            case 0:{ Collections.sort(registro, Comparadores.ordenarPorNombre); break; }
            case 1:{ Collections.sort(registro, Comparadores.ordenarPorCodigo); break; }
            case 2:{ Collections.sort(registro, Comparadores.ordenarPorCalorias); break; }
        }
        
        return registro;
    }
    
    private void buscarComidasPorCalorias(String calorias){
        switch(jComBoBuscarPorCalorias.getSelectedIndex()){
            case 0:{ llenarFilasDeLaTabla(comiData.listarComidasPorAproximacionCalorias(calorias+"%")); break; }
            case 1:{ llenarFilasDeLaTabla(comiData.listarComidasConMayorCantidadCalorias(Integer.valueOf(calorias))); break; }
            case 2:{ llenarFilasDeLaTabla(comiData.listarComidasConMenorCantidadCalorias(Integer.valueOf(calorias))); break; }
        }
    }
    
    private void vaciarFilasDeLaTabla(){
        modeloTab.setRowCount(0);
    }
    
//---------- Excepciones ----------
    private void excepcionCampoVacio(String dato) throws CampoVacioException {
        if(dato.isEmpty()){
            throw new CampoVacioException("Campo/s vacio");
        }
    }
    
    private void excepcionRangoNumerico(int num) throws RangoNumericoException {
        if(num < 1 || num > 10000){
            throw new RangoNumericoException("Solo se permiten valores entre 1 y 10.000");
        }
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
        jTabConsultasComidas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButSalir = new javax.swing.JButton();
        jButAgregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jComBoBuscarPorCalorias = new javax.swing.JComboBox<>();
        jComBoOrdenarPor = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jTexFiBuscar = new javax.swing.JTextField();
        jLabJTFBuscar = new javax.swing.JLabel();
        jButLimpiar = new javax.swing.JButton();

        jTabConsultasComidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTabConsultasComidas);

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

        jButSalir.setText("Salir");
        jButSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButSalirActionPerformed(evt);
            }
        });

        jButAgregar.setText("Agregar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(jButAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButSalir)
                    .addComponent(jButAgregar))
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultas COMIDAS");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("registro por selección");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(160, 160, 160))
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

        jComBoBuscarPorCalorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoBuscarPorCaloriasActionPerformed(evt);
            }
        });

        jComBoOrdenarPor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoOrdenarPorActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTexFiBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiBuscarKeyReleased(evt);
            }
        });

        jLabJTFBuscar.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jButLimpiar.setText("Limpiar");
        jButLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jComBoOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jComBoBuscarPorCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComBoBuscarPorCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComBoOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButLimpiar))
                        .addGap(0, 0, 0)
                        .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButSalirActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_jButSalirActionPerformed

    private void jComBoOrdenarPorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoOrdenarPorActionPerformed
        // TODO add your handling code here:
        
        try{
            String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
            int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);
            
            llenarTablaPersonalizado(caloriasCad);
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores
        }
    }//GEN-LAST:event_jComBoOrdenarPorActionPerformed

    private void jComBoBuscarPorCaloriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoBuscarPorCaloriasActionPerformed
        // TODO add your handling code here:
//        jTexFiBuscar.setText("");
//        jLabJTFBuscar.setText("");
//        llenarTablaPorDefecto();

        try{
            String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
            int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);
            
            llenarTablaPersonalizado(caloriasCad);
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores
        }
    }//GEN-LAST:event_jComBoBuscarPorCaloriasActionPerformed

    private void jTexFiBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiBuscarKeyReleased
        // TODO add your handling code here:
        jLabJTFBuscar.setText("");
        
        try{
            String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
            int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);
            llenarTablaPersonalizado(caloriasCad);
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            jLabJTFBuscar.setText("solo números");
        } catch(RangoNumericoException rne){
            jLabJTFBuscar.setText("desde 1 hasta 10.000");
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTexFiBuscarKeyReleased

    private void jButLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButLimpiarActionPerformed
        // TODO add your handling code here:
        jTexFiBuscar.setText("");
        jLabJTFBuscar.setText("");
        llenarTablaPorDefecto();
    }//GEN-LAST:event_jButLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButAgregar;
    private javax.swing.JButton jButLimpiar;
    private javax.swing.JButton jButSalir;
    private javax.swing.JComboBox<String> jComBoBuscarPorCalorias;
    private javax.swing.JComboBox<String> jComBoOrdenarPor;
    private javax.swing.JLabel jLabJTFBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTabConsultasComidas;
    private javax.swing.JTextField jTexFiBuscar;
    // End of variables declaration//GEN-END:variables
}
