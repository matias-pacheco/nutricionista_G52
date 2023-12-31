/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import nutricionista_g52.accesoADatos.DietaData;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.vistas.excepciones.CampoVacioException;
import nutricionista_g52.vistas.excepciones.FechaException;
import nutricionista_g52.vistas.excepciones.RangoNumericoException;

/**
 *
 * @author Matías Pacheco
 */
public class EditarDietaView extends javax.swing.JInternalFrame {
    private Dieta dieta;
    private DietaData dieData;
    
    /**
     * Creates new form ComidasView
     */
    public EditarDietaView() {
        initComponents();
    }
    
    public EditarDietaView(Dieta dieta) {
        initComponents();
        this.dieta = dieta;
        this.dieData = new DietaData();
        llenarCamposConDatosDelaDieta();
        setearJLabJTFHasta();
    }
    
//---------- Por Defecto -----------
    private void llenarCamposConDatosDelaDieta(){
        jDaChHasta.setDate(java.sql.Date.valueOf(dieta.getFechaFinal()));
        jTexFiPesoFinal.setText(String.valueOf(dieta.getPesoFinal()));
    }
    
    private void setearJLabJTFHasta(){
        jLabJTFHasta.setText(" DD/MM/YYYY");
    }

    private void limpiarCampos(){
        jDaChHasta.setDate(null);
        jTexFiPesoFinal.setText("");
    }
    
//---------- Excepciones ----------
    private void excepcionFecha(LocalDate fechaMayor, LocalDate fechaMenor) throws FechaException {
        if(fechaMayor.isBefore(fechaMenor)){
            throw new FechaException("La fecha final no puede ser ");
        }
    }
    
    private void excepcionCampoVacio(String dato) throws CampoVacioException {
        if(dato.isEmpty()){
            throw new CampoVacioException("Campo vacio. Ingrese el peso buscado del paciente");
        }
    }
    
    private void excepcionRangoNumerico(double num, double rangoMenor, double rangoMayor) throws RangoNumericoException {
        if(num < rangoMenor || num > rangoMayor){
            throw new RangoNumericoException("Solo se permiten valores entre \n"+(int)rangoMenor+",0 "
                    + "y "+(int)rangoMayor+",0");
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
        jLabel6 = new javax.swing.JLabel();
        jTexFiPesoFinal = new javax.swing.JTextField();
        jLabJTFPesoFinal = new javax.swing.JLabel();
        jDaChHasta = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabJTFHasta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButGuardar = new javax.swing.JButton();
        jButCancelar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar DIETA");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(12, 12, 12))
        );

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Peso Final");

        jTexFiPesoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiPesoFinalKeyReleased(evt);
            }
        });

        jLabJTFPesoFinal.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Hasta");

        jLabJTFHasta.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel18))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDaChHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTexFiPesoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabJTFHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jLabJTFPesoFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jDaChHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTexFiPesoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFPesoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButCancelar)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButCancelar)
                    .addComponent(jButGuardar))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        
        String ingSolo = "anterior al día vigente";
        try{
            Date hastaDate = jDaChHasta.getDate();
            LocalDate hasta = hastaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            excepcionFecha(hasta, dieta.getFechaInicial());
            ingSolo = "superar los dies años desde el día inicial de la dieta";
            excepcionFecha(dieta.getFechaInicial().plusYears(10), hasta);
            
            String pesoFinalCad = jTexFiPesoFinal.getText(); excepcionCampoVacio(pesoFinalCad);
            double pesoFinal = Double.parseDouble(pesoFinalCad); excepcionRangoNumerico(pesoFinal, 1, 600);
            
            Dieta dietaModi = new Dieta(dieta.getNombre(), dieta.getPaciente(), dieta.getFechaInicial(), dieta.getPesoInicial(), 
                    hasta, pesoFinal);
            dietaModi.setIdDieta(dieta.getIdDieta());
            
            if(dieData.modificarDieta(dietaModi)){
                limpiarCampos();
                this.dispose();
            }
        } catch(NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha valida en el formato indicado (DD/MM/YYYY)", "  Mensaje", 1);
        } catch(FechaException fe){
            JOptionPane.showMessageDialog(null, fe.getMessage()+ingSolo, "  Mensaje", 1);
        } catch(CampoVacioException cve){
            JOptionPane.showMessageDialog(null, cve.getMessage(), "  Mensaje", 1);
        } catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Ingrese unicamente números. Para indicar decimales utilize el punto (0.0)", "  Mensaje", 1);
        } catch(RangoNumericoException rne){
            JOptionPane.showMessageDialog(null, rne.getMessage(), "  Mensaje", 1);
        }
    }//GEN-LAST:event_jButGuardarActionPerformed

    private void jTexFiPesoFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiPesoFinalKeyReleased
        // TODO add your handling code here:
        jLabJTFPesoFinal.setText("");
        
        try{
            String pesoFinalCad = jTexFiPesoFinal.getText(); excepcionCampoVacio(pesoFinalCad);
            double pesoFinal = Double.parseDouble(pesoFinalCad); excepcionRangoNumerico(pesoFinal, 1, 600);
        } catch(CampoVacioException cve){
            //Capturo está excepción para evitar que intente parsear una cadena vacia
        } catch(NumberFormatException nfe){
            jLabJTFPesoFinal.setText("solo números (decimales con punto)");
        } catch(RangoNumericoException rne){
            jLabJTFPesoFinal.setText("desde 1 hasta 600");
        }
    }//GEN-LAST:event_jTexFiPesoFinalKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButCancelar;
    private javax.swing.JButton jButGuardar;
    private com.toedter.calendar.JDateChooser jDaChHasta;
    private javax.swing.JLabel jLabJTFHasta;
    private javax.swing.JLabel jLabJTFPesoFinal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTexFiPesoFinal;
    // End of variables declaration//GEN-END:variables
}
