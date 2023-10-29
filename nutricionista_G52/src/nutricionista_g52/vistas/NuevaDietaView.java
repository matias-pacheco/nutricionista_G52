/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas;

import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import nutricionista_g52.accesoADatos.DietaData;
import nutricionista_g52.accesoADatos.PacienteData;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.entidades.Paciente;
import nutricionista_g52.vistas.enumeraciones.NombreDeDieta;
import nutricionista_g52.vistas.excepciones.CampoVacioException;
import nutricionista_g52.vistas.excepciones.FechaException;
import nutricionista_g52.vistas.excepciones.ObjetoNuloException;
import nutricionista_g52.vistas.excepciones.RangoNumericoException;
import nutricionista_g52.vistas.excepciones.TipoDeDatoException;

/**
 *
 * @author Matías Pacheco
 */
public class NuevaDietaView extends javax.swing.JInternalFrame {
    private PacienteData pacData;
    private DietaData dieData;

    /**
     * Creates new form ComidasView
     */
    public NuevaDietaView() {
        initComponents();
        this.pacData = new PacienteData();
        this.dieData = new DietaData();
        porDefecto();
    }
    
//---------- Por Defecto ----------
    private void porDefecto(){
        habilitarComponentes(false);
        setearFechaJDaChDesde();
        listarJComBoNombre();
        visibilizarComponentes(false);
        setearJLabJTFHasta();
    }
    
    private void habilitarComponentes(boolean habilitado){
        jLabDesde.setEnabled(habilitado);
        jLabJTFDesde.setEnabled(habilitado);
        jLabJTFDesdeCO.setEnabled(habilitado);
        jDaChDesde.setEnabled(habilitado);
        jButAgregar.setEnabled(habilitado);
    }
    
    private void setearFechaJDaChDesde(){
        jDaChDesde.setDate(java.sql.Date.valueOf(LocalDate.now()));
    }
    
    private void listarJComBoNombre(){
        jComBoNombre.addItem("- seleccionar -");
        for(int i = 1; i <= 15; i++){
            jComBoNombre.addItem(seleccionarNombreDeDieta(i));
        }
        
        actualizarJComBoNombre();
    }
    
    private void visibilizarComponentes(boolean habilitado){
        jButAgregar.setVisible(habilitado);
    }
    
    private void setearJLabJTFHasta(){
        jLabJTFHasta.setText(" DD/MM/YYYY");
    }
    
//---------- Métodos Reune Métodos (método atajo)----------
    private void setearTextoEnCampoNro(int opcion, Object obj){
        switch(opcion){
            case 1:{ jComBoNombre.setSelectedIndex((int)obj); break; }
            case 2:{ jTexFiPaciente.setText((String)obj); break; }
            case 3:{ break; }
            case 4:{ jTexFiPesoInicial.setText((String)obj); break; }
            case 5:{ jDaChHasta.setDate(java.sql.Date.valueOf((LocalDate)obj)); break; }
            case 6:{ jTexFiPesoFinal.setText((String)obj); break; }
            case 7:{
                jComBoNombre.setSelectedIndex(0);
                jTexFiPaciente.setText((String)obj);
                jTexFiPesoInicial.setText((String)obj);
                jDaChHasta.setDate(null);
                jTexFiPesoFinal.setText((String)obj);
                break;
            }
        }
    }
    
    private void setearTextoEnEtiquetaNro(int opcion, String texto){
        switch(opcion){
            case 1:{ jLabJTFNombre.setText(texto); break; }
            case 2:{ jLabJTFPaciente.setText(texto); break; }
            case 3:{ break; }
            case 4:{ jLabJTFPesoInicial.setText(texto); break; }
            case 5:{ jLabJTFHasta.setText(texto); break;}
            case 6:{ jLabJTFPesoFinal.setText(texto); break; }
            case 7:{
//                jLabJTFNombre.setText(texto);
                jLabJTFPaciente.setText(texto);
                jLabJTFPesoInicial.setText(texto);
//                jLabJTFHasta.setText(texto);
                jLabJTFPesoFinal.setText(texto);
                break;
            }
        }
    }
    
    private String seleccionarNombreDeDieta(int opcion){
        String nombre = null;
        
        switch(opcion){
            case 1:{ nombre = NombreDeDieta.ATKINS.toString(); break; }
            case 2:{ nombre = NombreDeDieta.CETOGÉNICA.toString(); break; }
            case 3:{ nombre = NombreDeDieta.DASH.toString(); break; }
            case 4:{ nombre = NombreDeDieta.DETOX.toString(); break; }
            case 5:{ nombre = remplazarCaracterDeCadena("_", " ", NombreDeDieta.DE_LA_FERTILIDAD.toString()); break; }
            case 6:{ nombre = remplazarCaracterDeCadena("_"," ",NombreDeDieta.DE_LA_ZONA.toString()); break; }
            case 7:{ nombre = NombreDeDieta.HIPERCALÓRICA.toString(); break; }
            case 8:{ nombre = NombreDeDieta.HIPOCALÓRICA.toString(); break; }
            case 9:{ nombre = NombreDeDieta.KETO.toString(); break; }
            case 10:{ nombre = NombreDeDieta.MEDITERRÁNEA.toString(); break; }
            case 11:{ nombre = NombreDeDieta.ORNISH.toString(); break; }
            case 12:{ nombre = NombreDeDieta.PALEOLÍTICA.toString(); break; }
            case 13:{ nombre = NombreDeDieta.VEGANA.toString(); break; }
            case 14:{ nombre = NombreDeDieta.VEGETARIANA.toString(); break; }
            case 15:{ nombre = NombreDeDieta.VOLUMÉTRICA.toString(); break; }
        }
        
        return nombre;
    }
    
//----------  ----------
    private String remplazarCaracterDeCadena(String remplazar, String por, String cadena){
        String nombreCad = cadena.replace(remplazar, por);
        return nombreCad;
    }
    
    private String remplazarPatronDeCadena(String remplazar, String por, String cadena){
        String nombreCad = cadena.replaceAll(remplazar, por);
        return nombreCad;
    }
    
    private String removerAcentos(String cadena) {
        String normalizado = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return patron.matcher(normalizado).replaceAll("");
    }
    
    private void agregarNombreAlEnum(String nuevaDieta){
        NombreDeDieta enumeracion = NombreDeDieta.ATKINS;
        enumeracion.agregarDieta(nuevaDieta);
    }
    
    private void agregarNombresAlCombo(){
        NombreDeDieta enumeracion = NombreDeDieta.ATKINS;
        
        for(String nuevaDieta : enumeracion.getDietasAgregadas()){
            jComBoNombre.addItem(nuevaDieta);
        }
    }
    
    private void actualizarJComBoNombre(){
        for(int i = 16; i < jComBoNombre.getItemCount(); i++){
            jComBoNombre.removeItemAt(i);
            i--;
        }
        
        agregarNombresAlCombo();
    }
    
//---------- Excepciones ----------
    private void excepcionCampoVacio(String dato) throws CampoVacioException {
        if(dato.isEmpty()){
            throw new CampoVacioException("Campo/s vacio");
        }
    }
    
    private void excepcionRangoNumerico(int num, int rangoMenor, int rangoMayor) throws RangoNumericoException {
        if(num < rangoMenor || num > rangoMayor){
            throw new RangoNumericoException("Solo se permiten valores entre \n"+rangoMenor+" "
                    + "y "+rangoMayor+"");
        }
    }
    
    private void excepcionRangoNumerico(double num, double rangoMenor, double rangoMayor) throws RangoNumericoException {
        if(num < rangoMenor || num > rangoMayor){
            throw new RangoNumericoException("Solo se permiten valores entre \n"+(int)rangoMenor+",0 "
                    + "y "+(int)rangoMayor+",0");
        }
    }
    
    private void excepcionTipoDeDato(String dato, String expresionRegular) throws TipoDeDatoException {
        if(!dato.matches(expresionRegular)){
            throw new TipoDeDatoException("Tipo de dato invalido. Ingrese unicamente ");
        }
    }
    
    private void excepcionFecha(LocalDate fechaMayor, LocalDate fechaMenor) throws FechaException {
        if(fechaMayor.isBefore(fechaMenor)){
            throw new FechaException("La fecha final no puede ser ");
        }
    }
    
    private void excepcionObjetoNulo(Object obj) throws ObjetoNuloException {
        if(obj == null){
            throw new ObjetoNuloException("");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabDesde = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTexFiPaciente = new javax.swing.JTextField();
        jTexFiPesoFinal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabJTFDesdeCO = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabJTFNombre = new javax.swing.JLabel();
        jLabJTFPaciente = new javax.swing.JLabel();
        jLabJTFDesde = new javax.swing.JLabel();
        jLabJTFPesoFinal = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTexFiPesoInicial = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabJTFPesoInicial = new javax.swing.JLabel();
        jDaChDesde = new com.toedter.calendar.JDateChooser();
        jDaChHasta = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabJTFHasta = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComBoNombre = new javax.swing.JComboBox<>();
        jButAgregar = new javax.swing.JButton();
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
        jLabel1.setText("Nueva DIETA");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel1)
                .addGap(145, 145, 145))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(12, 12, 12))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Paciente (DNI)");

        jLabDesde.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabDesde.setText("Desde");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Peso Final");

        jTexFiPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiPacienteKeyReleased(evt);
            }
        });

        jTexFiPesoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiPesoFinalKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        jLabJTFDesdeCO.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabJTFDesdeCO.setForeground(new java.awt.Color(255, 0, 0));
        jLabJTFDesdeCO.setText("*");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabJTFNombre.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabJTFPaciente.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabJTFDesde.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabJTFPesoFinal.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setText("Peso Inicial");

        jTexFiPesoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiPesoInicialKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        jLabJTFPesoInicial.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Hasta");

        jLabJTFHasta.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("*");

        jComBoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComBoNombreActionPerformed(evt);
            }
        });

        jButAgregar.setText("Agregar");
        jButAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(55, 55, 55)
                                .addComponent(jComBoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jButAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel18))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jDaChHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel19))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTexFiPesoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel14))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTexFiPesoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel17))
                                    .addComponent(jLabJTFPesoInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabDesde)
                                .addGap(65, 65, 65)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabJTFDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jDaChDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabJTFDesdeCO)))))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTexFiPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12))
                            .addComponent(jLabJTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabJTFPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabJTFHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jLabJTFPesoFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComBoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jButAgregar))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabJTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTexFiPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabDesde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabJTFPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabJTFDesdeCO)
                            .addComponent(jDaChDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabJTFDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTexFiPesoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFPesoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jDaChHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(0, 0, 0)
                .addComponent(jLabJTFHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTexFiPesoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
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
                .addGap(129, 129, 129)
                .addComponent(jButCancelar)
                .addGap(12, 12, 12))
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
                .addGap(258, 258, 258))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        String ingSolo = "letras";
        try{
            int nombre = jComBoNombre.getSelectedIndex(); /*excepcionCampoVacio(nombre); excepcionTipoDeDato(nombre, "^[a-zA-ZÀ-ÖØ-öø-ÿ ]*$");*/
            
            if(nombre == 0){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un nombre", "  Mensaje", 1);
                return;
            }
            
            String dniCad = jTexFiPaciente.getText(); excepcionCampoVacio(dniCad);
            ingSolo = " del documento (sin puntuaciones)";
            int dniNro = (int) Long.parseLong(dniCad); excepcionRangoNumerico(dniNro, 1000000, 99999999);
            Paciente paciente = pacData.buscarpacientePorDni(dniNro); excepcionObjetoNulo(paciente);
            Date desdeDate = jDaChDesde.getDate();
            LocalDate desde = desdeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String pesoInicialCad = jTexFiPesoInicial.getText(); excepcionCampoVacio(pesoInicialCad);
            ingSolo = ". \nPara indicar decimales utilize el signo de puntuación (0.0)";
            double pesoInicial = Double.parseDouble(pesoInicialCad); excepcionRangoNumerico(pesoInicial, 1, 600);
            Date hastaDate = jDaChHasta.getDate();
            ingSolo = "anterior al día vigente";
            LocalDate hasta = hastaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); excepcionFecha(hasta, desde);
            ingSolo = "superar los dies años desde el día vigente";
            excepcionFecha(LocalDate.now().plusYears(10), hasta);
            
            String pesoFinalCad = jTexFiPesoFinal.getText(); excepcionCampoVacio(pesoFinalCad);
            ingSolo = ". \nPara indicar decimales utilize el signo de puntuación (0.0)";
            double pesoFinal = Double.parseDouble(pesoFinalCad); excepcionRangoNumerico(pesoFinal, 1, 600);

            Dieta dieta = new Dieta(seleccionarNombreDeDieta(nombre), paciente, desde, pesoInicial, hasta, pesoFinal);
            
            if(dieData.guardarDieta(dieta)){
                setearTextoEnCampoNro(7, "");
                
                this.dispose();
            }
        } catch(CampoVacioException cve){
            JOptionPane.showMessageDialog(null, cve.getMessage()+". Llene todos los campos indicados", "  Mensaje", 1);
        } catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Ingrese unicamente el número"+ingSolo, "  Mensaje", 1);
        } catch(RangoNumericoException rne){
            JOptionPane.showMessageDialog(null, rne.getMessage(), "  Mensaje", 1);
        } catch(ObjetoNuloException one){
            //Capturo está excepción para evitar msj posteriores hasta que ingrese un dato valido
        } catch(NullPointerException npe){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha valida en el formato indicado (DD/MM/YYYY)", "  Mensaje", 1);
        } catch(FechaException fe){
            JOptionPane.showMessageDialog(null, fe.getMessage()+ingSolo, "  Mensaje", 1);
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButGuardarActionPerformed

    private void jButLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButLimpiarActionPerformed
        // TODO add your handling code here:
        setearTextoEnCampoNro(7, "");
        setearTextoEnEtiquetaNro(7, "");
    }//GEN-LAST:event_jButLimpiarActionPerformed

    private void jButAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButAgregarActionPerformed
        // TODO add your handling code here:

        boolean entrada = false;
        do{
            try{
                String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese un nuevo nombre en la dieta", "  Nuevo Nombre", 1);
                excepcionCampoVacio(nuevoNombre); excepcionTipoDeDato(nuevoNombre, "^[a-zA-ZÀ-ÖØ-öø-ÿ ]*$");
                nuevoNombre = remplazarPatronDeCadena("\\s+", " ", nuevoNombre).trim().toUpperCase();

                for(int i = 0; i < jComBoNombre.getItemCount(); i++){
                    if(removerAcentos(nuevoNombre).equalsIgnoreCase(removerAcentos(jComBoNombre.getItemAt(i)))){
                        JOptionPane.showMessageDialog(null, "El nombre ingresado ya se encuentra en la lista", "  Mensaje", 1);
                        entrada = true;
                        break;
                    } else {
                        entrada = false;
                    }
                }
                
                if(!entrada){
                    agregarNombreAlEnum(nuevoNombre);
                    actualizarJComBoNombre();
                }
            } catch(NullPointerException npe){
                entrada = false;
            } catch(CampoVacioException cve){
                JOptionPane.showMessageDialog(null, "Campo Vacio. Debe llenar el campo", "  Mensaje", 1);
                entrada = true;
            } catch(TipoDeDatoException tdde){
                JOptionPane.showMessageDialog(null, tdde.getMessage()+"letras", "  Mensaje", 1);
                entrada = true;
            } catch(Exception e){
                e.printStackTrace();
            }
        } while(entrada);
    }//GEN-LAST:event_jButAgregarActionPerformed

    private void jComBoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoNombreActionPerformed
        // TODO add your handling code here:
        setearTextoEnEtiquetaNro(1, "");
        
        if(jComBoNombre.getSelectedIndex() == 0){
            setearTextoEnEtiquetaNro(1, "seleccione un nombre");
        }
    }//GEN-LAST:event_jComBoNombreActionPerformed

    private void jTexFiPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiPacienteKeyReleased
        // TODO add your handling code here:
        setearTextoEnEtiquetaNro(2, "");
        
        try{
            String dniCad = jTexFiPaciente.getText(); excepcionCampoVacio(dniCad);
            int dni = (int) Long.parseLong(dniCad); excepcionRangoNumerico(dni, 1000000, 99999999);
            
            if(dieData.isPacienteHaceDieta(dni)){
                setearTextoEnEtiquetaNro(2, "dieta vigente");
            }
        } catch(CampoVacioException cve){
            //Capturo está excepción para evitar que intente parsear una cadena vacia
        } catch(NumberFormatException nfe){
            setearTextoEnEtiquetaNro(2, "solo números");
        } catch(RangoNumericoException rne){
            setearTextoEnEtiquetaNro(2, "desde 1.000.000 hasta 99.999.999");
        }
    }//GEN-LAST:event_jTexFiPacienteKeyReleased

    private void jTexFiPesoInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiPesoInicialKeyReleased
        // TODO add your handling code here:
        setearTextoEnEtiquetaNro(4, "");
        
        try{
            String pesoInicialCad = jTexFiPesoInicial.getText(); excepcionCampoVacio(pesoInicialCad);
            double pesoInicial = Double.parseDouble(pesoInicialCad); excepcionRangoNumerico(pesoInicial, 1, 600);
        } catch(CampoVacioException cve){
            //Capturo está excepción para evitar que intente parsear una cadena vacia
        } catch(NumberFormatException nfe){
            setearTextoEnEtiquetaNro(4, "solo números (decimales con punto)");
        } catch(RangoNumericoException rne){
            setearTextoEnEtiquetaNro(4, "desde 1 hasta 600");
        }
    }//GEN-LAST:event_jTexFiPesoInicialKeyReleased

    private void jTexFiPesoFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTexFiPesoFinalKeyReleased
        // TODO add your handling code here:
        setearTextoEnEtiquetaNro(6, "");
        
        try{
            String pesoFinalCad = jTexFiPesoFinal.getText(); excepcionCampoVacio(pesoFinalCad);
            double pesoFinal = Double.parseDouble(pesoFinalCad); excepcionRangoNumerico(pesoFinal, 1, 600);
        } catch(CampoVacioException cve){
            //Capturo está excepción para evitar que intente parsear una cadena vacia
        } catch(NumberFormatException nfe){
            setearTextoEnEtiquetaNro(6, "solo números (decimales con punto)");
        } catch(RangoNumericoException rne){
            setearTextoEnEtiquetaNro(6, "desde 1 hasta 600");
        }
    }//GEN-LAST:event_jTexFiPesoFinalKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButAgregar;
    private javax.swing.JButton jButCancelar;
    private javax.swing.JButton jButGuardar;
    private javax.swing.JButton jButLimpiar;
    private javax.swing.JComboBox<String> jComBoNombre;
    private com.toedter.calendar.JDateChooser jDaChDesde;
    private com.toedter.calendar.JDateChooser jDaChHasta;
    private javax.swing.JLabel jLabDesde;
    private javax.swing.JLabel jLabJTFDesde;
    private javax.swing.JLabel jLabJTFDesdeCO;
    private javax.swing.JLabel jLabJTFHasta;
    private javax.swing.JLabel jLabJTFNombre;
    private javax.swing.JLabel jLabJTFPaciente;
    private javax.swing.JLabel jLabJTFPesoFinal;
    private javax.swing.JLabel jLabJTFPesoInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTexFiPaciente;
    private javax.swing.JTextField jTexFiPesoFinal;
    private javax.swing.JTextField jTexFiPesoInicial;
    // End of variables declaration//GEN-END:variables
}
