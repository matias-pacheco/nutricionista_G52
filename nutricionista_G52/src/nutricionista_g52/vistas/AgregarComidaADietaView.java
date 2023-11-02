/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import javax.swing.JOptionPane;
import nutricionista_g52.accesoADatos.Dieta_ComidaData;
import nutricionista_g52.entidades.Comida;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.entidades.Dieta_Comida;
import nutricionista_g52.entidades.enumeraciones.HorarioAlimenticio;
import nutricionista_g52.vistas.excepciones.CampoVacioException;
import nutricionista_g52.vistas.excepciones.RangoNumericoException;

/**
 *
 * @author Matías Pacheco
 */
public class AgregarComidaADietaView extends javax.swing.JInternalFrame {
    private Comida comida;
    private Dieta dieta;
    private Dieta_ComidaData dieComiData;
    
    /**
     * Creates new form ComidasView
     */
    public AgregarComidaADietaView() {
        initComponents();
    }
    
    public AgregarComidaADietaView(Comida comida, Dieta dieta) {
        initComponents();
        this.comida = comida;
        this.dieta = dieta;
        this.dieComiData = new Dieta_ComidaData();
        porDefecto();
    }
    
//----------- Por Defecto ----------
    private void porDefecto(){
        llenarCamposDeComida();
        listarJComBoHorario();
    }
    
    private void llenarCamposDeComida(){
        jLabComidaNombre.setText(comida.getNombre());
        jLabCaloriasNro.setText(String.valueOf(comida.getCantCalorias()));
    }
    
    private void listarJComBoHorario(){
        jComBoHorario.addItem("- seleccionar -");
        jComBoHorario.addItem(HorarioAlimenticio.DESAYUNO.toString());
        jComBoHorario.addItem(HorarioAlimenticio.ALMUERZO.toString());
        jComBoHorario.addItem(HorarioAlimenticio.MERIENDA.toString());
        jComBoHorario.addItem(HorarioAlimenticio.CENA.toString());
        jComBoHorario.addItem(HorarioAlimenticio.SNACK.toString());
    }
    
    private void limpiarCampos(){
        jComBoHorario.setSelectedIndex(0);
        jTexFiPorcion.setText("");
        jLabJTFPorcion.setText("");
    }
    
//---------- ----------
    private HorarioAlimenticio seleccionarHorario(int opcion){
        HorarioAlimenticio horario = null;
        
        switch(opcion){
            case 1:{ horario = HorarioAlimenticio.DESAYUNO; break; }
            case 2:{ horario = HorarioAlimenticio.ALMUERZO; break; }
            case 3:{ horario = HorarioAlimenticio.MERIENDA; break; }
            case 4:{ horario = HorarioAlimenticio.CENA; break; }
            case 5:{ horario = HorarioAlimenticio.SNACK; break; }
        }
        
        return horario;
    }
    
//---------- Excepciones -----------
    private void excepcionCampoVacio(String dato) throws CampoVacioException {
        if(dato.isEmpty()){
            throw new CampoVacioException("Campo/s vacio");
        }
    }
    
    private void excepcionRangoNumerico(int num) throws RangoNumericoException {
        if(num < 1 || num > 1000){
            throw new RangoNumericoException("Solo se permiten valores entre 1 y 1.000");
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabDesde = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTexFiPorcion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabJTFPorcion = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabJTFHorario = new javax.swing.JLabel();
        jComBoHorario = new javax.swing.JComboBox<>();
        jLabCaloriasNro = new javax.swing.JLabel();
        jLabComidaNombre = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButGuardar = new javax.swing.JButton();
        jButCancelar = new javax.swing.JButton();
        jButLimpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar COMIDA a DIETA");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(12, 12, 12))
        );

        jLabDesde.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabDesde.setText("Comida");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Porción");

        jTexFiPorcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiPorcionKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabJTFPorcion.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setText("Calorias");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Horario");

        jLabJTFHorario.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jComBoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoHorarioActionPerformed(evt);
            }
        });

        jLabCaloriasNro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabCaloriasNro.setText("Calorias");

        jLabComidaNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabComidaNombre.setText("Comida");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("kcal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabDesde)
                    .addComponent(jLabel18)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTexFiPorcion, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabComidaNombre)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabCaloriasNro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jComBoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel11))
                        .addComponent(jLabJTFPorcion, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addComponent(jLabJTFHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabDesde)
                    .addComponent(jLabComidaNombre))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabCaloriasNro)
                    .addComponent(jLabel2))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jComBoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTexFiPorcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFPorcion, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButGuardar.setText("Guardar");
        jButGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButGuardarActionPerformed(evt);
            }
        });

        jButCancelar.setText("Cancelar");
        jButCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButCancelarActionPerformed(evt);
            }
        });

        jButLimpiar.setText("Limpiar");
        jButLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jButCancelar)
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButCancelar)
                    .addComponent(jButGuardar)
                    .addComponent(jButLimpiar))
                .addGap(12, 12, 12))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("campos obligatorios");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(221, 221, 221))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButCancelarActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_jButCancelarActionPerformed

    private void jButGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButGuardarActionPerformed
        // TODO add your handling code here:
        
        int horarioOpc = jComBoHorario.getSelectedIndex();
       
        if(horarioOpc == 0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un horario", "  Mensaje", 1);
            return;
        }
        
        try{
            String porcionCad = jTexFiPorcion.getText(); excepcionCampoVacio(porcionCad);
            int porcion = (int) Long.parseLong(porcionCad); excepcionRangoNumerico(porcion);
            
            Dieta_Comida dietaComida = new Dieta_Comida(comida, dieta, seleccionarHorario(horarioOpc), porcion);
            
            if(dieComiData.guardarDieta_Comida(dietaComida)){
                limpiarCampos();
                
                this.dispose();
            }
        } catch(CampoVacioException cve){
            JOptionPane.showMessageDialog(null, "Campo Vacio. Ingrese la porción de comida a ingerir", "  Mensaje", 1);
        } catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Ingrese unicamente números", "  Mensaje", 1);
        } catch(RangoNumericoException rne){
            JOptionPane.showMessageDialog(null, rne.getMessage(), "  Mensaje", 1);
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButGuardarActionPerformed

    private void jButLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_jButLimpiarActionPerformed

    private void jComBoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoHorarioActionPerformed
        // TODO add your handling code here:
        jLabJTFHorario.setText("");
        
        int horarioNro = jComBoHorario.getSelectedIndex();
        if(horarioNro == 0){
            jLabJTFHorario.setText("seleccione un horario");
        }
    }//GEN-LAST:event_jComBoHorarioActionPerformed

    private void jTexFiPorcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiPorcionKeyReleased
        // TODO add your handling code here:
        jLabJTFPorcion.setText("");
        
        try{
            String porcionCad = jTexFiPorcion.getText(); excepcionCampoVacio(porcionCad);
            int porcion = (int) Long.parseLong(porcionCad); excepcionRangoNumerico(porcion);
        } catch(CampoVacioException cve){
            //Capturo está excepción para evitar que envie un msj al parsear una cadena vacia
        } catch(NumberFormatException nfe){
            jLabJTFPorcion.setText("solo números");
        } catch(RangoNumericoException rne){
            jLabJTFPorcion.setText("desde 1 hasta 1.000");
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTexFiPorcionKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButCancelar;
    private javax.swing.JButton jButGuardar;
    private javax.swing.JButton jButLimpiar;
    private javax.swing.JComboBox<String> jComBoHorario;
    private javax.swing.JLabel jLabCaloriasNro;
    private javax.swing.JLabel jLabComidaNombre;
    private javax.swing.JLabel jLabDesde;
    private javax.swing.JLabel jLabJTFHorario;
    private javax.swing.JLabel jLabJTFPorcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTexFiPorcion;
    // End of variables declaration//GEN-END:variables
}