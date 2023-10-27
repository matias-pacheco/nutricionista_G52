/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import java.awt.HeadlessException;
import java.util.Collections;
import nutricionista_g52.entidades.Paciente;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import nutricionista_g52.accesoADatos.PacienteData;
import nutricionista_g52.vistas.EditarPacienteView;
import nutricionista_g52.vistas.MenuPrincipalView;
import static nutricionista_g52.vistas.MenuPrincipalView.jDesPaEscritorio;
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
public class PacientesView extends javax.swing.JInternalFrame {
    private DefaultTableModel modeloTab;
    private PacienteData pacData;

    /**
     * Creates new form PacientesView
     */
    public PacientesView() {
        initComponents();
        this.modeloTab = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
        };
        this.pacData = new PacienteData();
        porDefecto();
    }
    
//---------- Por defecto ----------
    private void porDefecto(){
        agregarColumnasALaTabla();
        listarJComBoBuscar();
        deshabilitarElReordenamientoDeColumnas();
        habilitarComponentes(true);
    }
    
    private void listarJComBoBuscar(){
        jComBoBuscar.addItem("POR APELLIDO");
        jComBoBuscar.addItem("POR DNI");
    }
    
    private void agregarColumnasALaTabla(){
        modeloTab.addColumn("DNI");
        modeloTab.addColumn("APELLIDO");
        modeloTab.addColumn("NOMBRE");
        modeloTab.addColumn("DOMICILIO");
        modeloTab.addColumn("TELÉFONO");
        
        jTabPacientes.setModel(modeloTab);
    }
    
    private void deshabilitarElReordenamientoDeColumnas(){
        jTabPacientes.getTableHeader().setReorderingAllowed(false);
    }
    
    private void habilitarComponentes(boolean habilitado){
        jButNuevo.setEnabled(habilitado);
        jButEditar.setEnabled(habilitado);
        jButEliminar.setEnabled(habilitado);
        jButRestaurar.setEnabled(!habilitado);
    }
    
//---------- Métodos Reune Métodos (método atajo)----------  
    private void llenarPorDefecto(){
        if(jCheBoSoloPacientesBorrados.isSelected()){
            vaciarFilasDeLaTabla();
            llenarFilasDeLaTabla(pacData.listarPacientesBorrados());
        } else {
            vaciarFilasDeLaTabla();
            llenarFilasDeLaTabla(pacData.listarPacientes());
        }
            
    }
    
    private void llenarPersonalizado(List<Paciente> registro){
        vaciarFilasDeLaTabla();
        llenarFilasDeLaTabla(registro);
    }
    
//---------- Listar, Ordenar, Vaciar, Seleccionar ----------
    private void llenarFilasDeLaTabla(List<Paciente> registro){
        for(Paciente paciente : ordenarFilasDeLaTabla(registro)){
            modeloTab.addRow(new Object[] {paciente.getDni(), paciente.getApellido(), paciente.getNombre(), paciente.getDomicilio(),
                paciente.getTelefono()});
        }
    }
    
    private void llenarFilaDeLaTabla(Paciente paciente){
        try{
            modeloTab.addRow(new Object[] {paciente.getDni(), paciente.getApellido(), paciente.getNombre(), paciente.getDomicilio(),
                paciente.getTelefono()});
        } catch(NullPointerException npe){
            //Captura la excepción unicamente para que el programa responda adecuadamente
        }
    }
    
    private List<Paciente> ordenarFilasDeLaTabla(List<Paciente> registro){
        if(jComBoBuscar.getSelectedIndex() == 0){
            Collections.sort(registro, Comparadores.ordenarPorApellido);
        } else {
            Collections.sort(registro, Comparadores.ordenarPorDni);
        }
        
        return registro;
    }
    
    private void vaciarFilasDeLaTabla(){
        for(int i = 0; i < modeloTab.getRowCount(); i++){
            modeloTab.removeRow(i);
            i--;
        }
    }
    
    private void vaciarJTexFiBuscar(){
        jTexFiBuscar.setText("");
    }
    
    private Paciente seleccionarPacienteEnTabla(){
        int dni = (int) jTabPacientes.getValueAt(jTabPacientes.getSelectedRow(), 0);
//        System.out.println("dni: "+dni);
        Paciente paciente = pacData.buscarpacientePorDni(dni);
        
        return paciente;
    }
    
    private int seleccionarDniEnTabla(){
        int dni = (int) jTabPacientes.getValueAt(jTabPacientes.getSelectedRow(), 0);
        
        return dni;
    }

//---------- Excepciones ----------
    private void excepcionCampoVacio(String dato) throws CampoVacioException {
        if(dato.isEmpty()){
            throw new CampoVacioException("Campo/s vacio");
        }
    }
    
    private void excepcionRangoNumerico(int num, int rangoMenor, int rangoMayor) throws RangoNumericoException {
        if(num < rangoMenor || num > rangoMayor){
            throw new RangoNumericoException("Solo se permiten valores entre 1.000.000 y 99.999.999");
        }
    }
    
    private void excepcionTipoDeDato(String dato) throws TipoDeDatoException {
        if(!dato.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ ]*$")){
            throw new TipoDeDatoException("Tipo de dato invalido. Ingrese unicamente letras");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabPacientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButEditar = new javax.swing.JButton();
        jButEliminar = new javax.swing.JButton();
        jButSalir = new javax.swing.JButton();
        jButRestaurar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButNuevo = new javax.swing.JButton();
        jTexFiBuscar = new javax.swing.JTextField();
        jComBoBuscar = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabJTFBuscar = new javax.swing.JLabel();
        jCheBoSoloPacientesBorrados = new javax.swing.JCheckBox();

        jTabPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(jTabPacientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel1.setText("PACIENTES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(263, 263, 263)
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

        jButNuevo.setText("Nuevo");
        jButNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButNuevoActionPerformed(evt);
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

        jCheBoSoloPacientesBorrados.setText("solo pacientes borrados");
        jCheBoSoloPacientesBorrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoSoloPacientesBorradosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jCheBoSoloPacientesBorrados)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jComBoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButNuevo)
                            .addComponent(jComBoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheBoSoloPacientesBorrados))
                        .addGap(0, 0, 0)
                        .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            if(jTabPacientes.getSelectedRow() == -1){
                try{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un paciente de la tabla", "  Mensaje", 1);
                    return;
                } catch(HeadlessException he){
                    System.err.println(he.getMessage());
                }
            }
            
            EditarPacienteView ediPacV = new EditarPacienteView(seleccionarPacienteEnTabla());
            ediPacV.setVisible(true);
        
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - ediPacV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - ediPacV.getHeight()) / 2;
        
            ediPacV.setLocation(x, y);
        
            MenuPrincipalView.jDesPaEscritorio.add(ediPacV);
        
            ediPacV.moveToFront();
        }
    }//GEN-LAST:event_jButEditarActionPerformed

    private void jButNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButNuevoActionPerformed
        // TODO add your handling code here

        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 2){
            NuevoPacienteView newPacV = new NuevoPacienteView();
            newPacV.setVisible(true);

            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - newPacV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - newPacV.getHeight()) / 2;

            newPacV.setLocation(x, y);

            MenuPrincipalView.jDesPaEscritorio.add(newPacV);

            newPacV.toFront();
            //            newPacV.setBorder(new EmptyBorder(0, 0, 0, 0));
            //            ((BasicInternalFrameUI) newPacV.getUI()).setNorthPane(null);

            //            System.out.println(MenuPrincipalView.jDesPaEscritorio.getComponentCount());
        }
    }//GEN-LAST:event_jButNuevoActionPerformed

    private void jButSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButSalirActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_jButSalirActionPerformed

    private void jComBoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoBuscarActionPerformed
        // TODO add your handling code here:
        
        vaciarJTexFiBuscar();
        jLabJTFBuscar.setText("");
        llenarPorDefecto();
        
//        try{
//            String dato = jTexFiBuscar.getText(); excepcionCampoVacio(dato);
//
//            if(jComBoBuscar.getSelectedIndex() == 0){
//                excepcionTipoDeDato(dato);
//                
//                jLabJTFBuscar.setText(title);
//            } else {
//                int dni = (int) Long.parseLong(dato);
//                
//                jLabJTFBuscar.setText("");
//            }
//        } catch(CampoVacioException cve){
//            llenarPorDefecto();
//        } catch(TipoDeDatoException tdde){
//            jLabJTFBuscar.setText("");
//            vaciarJTexFiBuscar();
//            llenarPorDefecto();
//        } catch(NumberFormatException nfe){
//            jLabJTFBuscar.setText("");
//            vaciarJTexFiBuscar();
//            llenarPorDefecto();
//        }
    }//GEN-LAST:event_jComBoBuscarActionPerformed

    private void jButEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButEliminarActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jTabPacientes.getSelectedRow() != -1){
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este paciente?", "  Eliminar Paciente", 0);
                
                if(opcion == 0){
                    pacData.borrarPacientePorDni(seleccionarDniEnTabla());
                    llenarPorDefecto();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un paciente de la tabla", "  Mensaje", 1);
            }
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        }
    }//GEN-LAST:event_jButEliminarActionPerformed

    private void jTexFiBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiBuscarKeyReleased
        // TODO add your handling code here:
        jLabJTFBuscar.setText("");
        
        try{
            String dato = jTexFiBuscar.getText(); excepcionCampoVacio(dato);
            
            if(jCheBoSoloPacientesBorrados.isSelected()){
                if(jComBoBuscar.getSelectedIndex() == 0){
                    excepcionTipoDeDato(dato);
                    
                    llenarPersonalizado(pacData.listarPacientesBorradosPorApellido(dato+"%"));
                    return;
                }
                
                int dniNro = (int) Long.parseLong(dato); excepcionRangoNumerico(dniNro, 1, 99999999);
                
                llenarPersonalizado(pacData.listarPacientesBorradosPorDni(dato+"%"));
                return;
            }
            
            
            if(jComBoBuscar.getSelectedIndex() == 0){
                excepcionTipoDeDato(dato);

                llenarPersonalizado(pacData.listarPacientesPorApellido(dato+"%"));
                return;
            }
            
            int dniNro = (int) Long.parseLong(dato); excepcionRangoNumerico(dniNro, 1, 99999999);
                
            llenarPersonalizado(pacData.listarPacientesPorDni(dato+"%"));
        } catch(CampoVacioException cve){
            llenarPorDefecto();
        } catch(TipoDeDatoException tdde){
            jLabJTFBuscar.setText("solo letras");
        } catch(NumberFormatException nfe){
            jLabJTFBuscar.setText("solo números");
        } catch(RangoNumericoException rne){
            jLabJTFBuscar.setText("desde 1.000.000 hasta 99.999.999");
        }
    }//GEN-LAST:event_jTexFiBuscarKeyReleased

    private void jCheBoSoloPacientesBorradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoSoloPacientesBorradosActionPerformed
        // TODO add your handling code here:

        llenarPorDefecto();

        if(jCheBoSoloPacientesBorrados.isSelected()){
            habilitarComponentes(false);
        } else {
            habilitarComponentes(true);
        }

    }//GEN-LAST:event_jCheBoSoloPacientesBorradosActionPerformed

    private void jButRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButRestaurarActionPerformed
        // TODO add your handling code here:

        try{
            if(jTabPacientes.getSelectedRow() != -1){
                int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea restaurar este paciente?", "  Restaurar Paciente", 0);

                if(opcion == 0){
                    pacData.restaurarPacientePorDni(seleccionarDniEnTabla());
                    llenarPorDefecto();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un paciente de la tabla", "  Mensaje", 1);
            }
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        }
    }//GEN-LAST:event_jButRestaurarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButEditar;
    private javax.swing.JButton jButEliminar;
    private javax.swing.JButton jButNuevo;
    private javax.swing.JButton jButRestaurar;
    private javax.swing.JButton jButSalir;
    private javax.swing.JCheckBox jCheBoSoloPacientesBorrados;
    private javax.swing.JComboBox<String> jComBoBuscar;
    private javax.swing.JLabel jLabJTFBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTabPacientes;
    private javax.swing.JTextField jTexFiBuscar;
    // End of variables declaration//GEN-END:variables
}
