/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import java.awt.HeadlessException;
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
import nutricionista_g52.vistas.excepciones.TipoDeDatoException;

/**
 *
 * @author Matías Pacheco
 */
public class ComidasView extends javax.swing.JInternalFrame {
    private DefaultTableModel modeloTab;
    private ComidaData comiData;
    /**
     * Creates new form PacientesView
     */
    public ComidasView() {
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
        listarJComboBoBuscar();
        agregarColumnasALaTabla();
        llenarPorDefecto();
        deshabilitarElReordenamientoDeColumnas();
        habilitarComponentes(true);
    }
    
    private void listarJComboBoBuscar(){
        jComBoBuscar.addItem("POR NOMBRE");
        jComBoBuscar.addItem("POR CÓDIGO");
    }
    
    private void agregarColumnasALaTabla(){
        modeloTab.addColumn("CÓDIGO");
        modeloTab.addColumn("NOMBRE");
        modeloTab.addColumn("DETALLE");
        modeloTab.addColumn("CALORIAS");
        
        jTabComidas.setModel(modeloTab);
    }
    
    private void deshabilitarElReordenamientoDeColumnas(){
        jTabComidas.getTableHeader().setReorderingAllowed(false);
    }
    
    private void habilitarComponentes(boolean habilitado){
        jButNueva.setEnabled(habilitado);
        jButEditar.setEnabled(habilitado);
        jButEliminar.setEnabled(habilitado);
        jButRestaurar.setEnabled(!habilitado);
    }
    
//---------- Métodos Reune Métodos (método atajo)----------  
    private void llenarPorDefecto(){
        vaciarFilasDeLaTabla();
        
        if(jCheBoSoloComidasBorradas.isSelected()){
            llenarFilasDeLaTabla(comiData.listarComidasBorradas());
        } else {
            llenarFilasDeLaTabla(comiData.listarComidas());
        }
    }
    
    private void llenarPersonalizado(List<Comida> registro){
        vaciarFilasDeLaTabla();
        llenarFilasDeLaTabla(registro);
    }
    
//---------- Listar, Ordenar, Seleccionar, Vaciar ----------
    private void llenarFilasDeLaTabla(List<Comida> registro){
        for(Comida comida : ordenarFilasDeLaTabla(registro)){
            modeloTab.addRow(new Object[] {comida.getIdComida(), comida.getNombre(), comida.getDetalle(), comida.getCantCalorias()});
        }
    }
    
    private List<Comida> ordenarFilasDeLaTabla(List<Comida> registro){
        if(jComBoBuscar.getSelectedIndex() == 0){
            Collections.sort(registro, Comparadores.ordenarPorNombre);
        } else {
            Collections.sort(registro, Comparadores.ordenarPorCodigo);
        }
        
        return registro;
    }
    
    private Comida seleccionarComidaEnTabla(){
        int codigo = (int) jTabComidas.getValueAt(jTabComidas.getSelectedRow(), 0);
        Comida comida = comiData.buscarComidaPorId(codigo);
        return comida;
    }
    
    private int seleccionarCodigoEnTabla(){
        int codigo = (int) jTabComidas.getValueAt(jTabComidas.getSelectedRow(), 0);
        return codigo;
    }
    
    private void vaciarFilasDeLaTabla(){
        for(int i = 0; i < modeloTab.getRowCount(); i++){
            modeloTab.removeRow(i);
            i--;
        }
    }
    
    private void JTexFiBuscar(){
        jTexFiBuscar.setText("");
    }
    
//---------- Excepciones ----------
    private void excepcionCampoVacio(String dato) throws CampoVacioException {
        if(dato.isEmpty()){
            throw new CampoVacioException("Campo/s vacio");
        }
    }
    
    private void excepcionTipoDeDato(String dato) throws TipoDeDatoException {
        if(!dato.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ ]*$")){
            throw new TipoDeDatoException("Tipo de dato invalido. Ingrese unicamente letras");
        }
    }
    
    private void excepcionRangoNumerico(int num, int rangoMenor, int rangoMayor) throws RangoNumericoException {
        if(num < rangoMenor || num > rangoMayor){
            throw new RangoNumericoException("Solo se permiten valores entre 1 y 2.147.483.647");
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
        jTabComidas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButEditar = new javax.swing.JButton();
        jButEliminar = new javax.swing.JButton();
        jButSalir = new javax.swing.JButton();
        jButRestaurar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButNueva = new javax.swing.JButton();
        jTexFiBuscar = new javax.swing.JTextField();
        jComBoBuscar = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabJTFBuscar = new javax.swing.JLabel();
        jCheBoSoloComidasBorradas = new javax.swing.JCheckBox();

        jTabComidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTabComidas);

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

        jButEditar.setText("Editar");
        jButEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButEditarActionPerformed(evt);
            }
        });

        jButEliminar.setText("Eliminar");
        jButEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButEliminarActionPerformed(evt);
            }
        });

        jButSalir.setText("Salir");
        jButSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButSalirActionPerformed(evt);
            }
        });

        jButRestaurar.setText("Restaurar");
        jButRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButRestaurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224)
                .addComponent(jButSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButEditar)
                    .addComponent(jButEliminar)
                    .addComponent(jButSalir)
                    .addComponent(jButRestaurar))
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COMIDAS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(12, 12, 12))
        );

        jButNueva.setText("Nueva");
        jButNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButNuevaActionPerformed(evt);
            }
        });

        jTexFiBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiBuscarKeyReleased(evt);
            }
        });

        jComBoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoBuscarActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabJTFBuscar.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jCheBoSoloComidasBorradas.setText("solo comidas borradas");
        jCheBoSoloComidasBorradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoSoloComidasBorradasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jCheBoSoloComidasBorradas)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComBoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButNueva)
                    .addComponent(jComBoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheBoSoloComidasBorradas))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButEditarActionPerformed
        // TODO add your handling code here:
        
        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 2){
            if(jTabComidas.getSelectedRow() == -1){
                try{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una comida de la tabla", "  Mensaje", 1);
                    return;
                } catch(HeadlessException he){
                    System.err.println(he.getMessage());
                }
            }
            
            EditarComidaView ediComiV = new EditarComidaView(seleccionarComidaEnTabla());
            ediComiV.setVisible(true);
        
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - ediComiV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - ediComiV.getHeight()) / 2;
        
            ediComiV.setLocation(x, y);
        
            MenuPrincipalView.jDesPaEscritorio.add(ediComiV);
        
            ediComiV.moveToFront();
        }
    }//GEN-LAST:event_jButEditarActionPerformed

    private void jButNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButNuevaActionPerformed
        // TODO add your handling code here

        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 2){
            NuevaComidaView newComiV = new NuevaComidaView();
            newComiV.setVisible(true);

            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - newComiV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - newComiV.getHeight()) / 2;

            newComiV.setLocation(x, y);

            MenuPrincipalView.jDesPaEscritorio.add(newComiV);

            newComiV.toFront();
            //            newPacV.setBorder(new EmptyBorder(0, 0, 0, 0));
            //            ((BasicInternalFrameUI) newPacV.getUI()).setNorthPane(null);

            //            System.out.println(MenuPrincipalView.jDesPaEscritorio.getComponentCount());
        }
    }//GEN-LAST:event_jButNuevaActionPerformed

    private void jButSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButSalirActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_jButSalirActionPerformed

    private void jButEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButEliminarActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jTabComidas.getSelectedRow() != -1){
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta comida?", "  Eliminar Comida", 0);
                
                if(opcion == 0){
                    comiData.borrarComida(seleccionarCodigoEnTabla());
                    vaciarFilasDeLaTabla();
                    llenarPorDefecto();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe sellecionar una comida de la tabla", "  Mensaje", 1);
            }
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        }
    }//GEN-LAST:event_jButEliminarActionPerformed

    private void jCheBoSoloComidasBorradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoSoloComidasBorradasActionPerformed
        // TODO add your handling code here:
        
        llenarPorDefecto();
        
        if(jCheBoSoloComidasBorradas.isSelected()){
            habilitarComponentes(false);
        } else {
            habilitarComponentes(true);
        }
    }//GEN-LAST:event_jCheBoSoloComidasBorradasActionPerformed

    private void jButRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButRestaurarActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jTabComidas.getSelectedRow() != -1){
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta comida?", "  Eliminar Comida", 0);

                if(opcion == 0){
                    comiData.restaurarComida(seleccionarCodigoEnTabla());
                    llenarPorDefecto();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una comida de la tabla", "  Mensaje", 1);
            }
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        }
    }//GEN-LAST:event_jButRestaurarActionPerformed

    private void jComBoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoBuscarActionPerformed
        // TODO add your handling code here:
        JTexFiBuscar();
        jLabJTFBuscar.setText("");
        llenarPorDefecto();
    }//GEN-LAST:event_jComBoBuscarActionPerformed

    private void jTexFiBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiBuscarKeyReleased
        // TODO add your handling code here:
        jLabJTFBuscar.setText("");
        
        try{
            String entrada = jTexFiBuscar.getText(); excepcionCampoVacio(entrada);
            
            if(jCheBoSoloComidasBorradas.isSelected()){
                if(jComBoBuscar.getSelectedIndex() == 0){
                    excepcionTipoDeDato(entrada);
                    
                    llenarPersonalizado(comiData.listarComidasBorradasPorNombre(entrada+"%"));
                    return;
                }
                
                int codigo = (int) Long.parseLong(entrada); excepcionRangoNumerico(codigo, 1, 2147483647);
                
                llenarPersonalizado(comiData.listarComidasBorradasPorIdComida(entrada+"%"));
                return;
            }
            
            if(jComBoBuscar.getSelectedIndex() == 0){
                excepcionTipoDeDato(entrada);
                
                llenarPersonalizado(comiData.listarComidasPorNombre(entrada+"%"));
                return;
            }
            
            int codigo = (int) Long.parseLong(entrada); excepcionRangoNumerico(codigo, 1, 2147483647);
            
            llenarPersonalizado(comiData.listarComidasPorIdComida(entrada));
        } catch(CampoVacioException cve){
            llenarPorDefecto();
        } catch(TipoDeDatoException tdde){
            jLabJTFBuscar.setText("solo letras");
        } catch(NumberFormatException nfe){
            jLabJTFBuscar.setText("solo números enteros");
        } catch(RangoNumericoException rne){
            jLabJTFBuscar.setText("desde 1 hasta 2.147.483.647");
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTexFiBuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButEditar;
    private javax.swing.JButton jButEliminar;
    private javax.swing.JButton jButNueva;
    private javax.swing.JButton jButRestaurar;
    private javax.swing.JButton jButSalir;
    private javax.swing.JCheckBox jCheBoSoloComidasBorradas;
    private javax.swing.JComboBox<String> jComBoBuscar;
    private javax.swing.JLabel jLabJTFBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTabComidas;
    private javax.swing.JTextField jTexFiBuscar;
    // End of variables declaration//GEN-END:variables
}
