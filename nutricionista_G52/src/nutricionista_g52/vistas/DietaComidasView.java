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
import nutricionista_g52.accesoADatos.Dieta_ComidaData;
import nutricionista_g52.entidades.Comida;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.entidades.Dieta_Comida;
import nutricionista_g52.entidades.enumeraciones.HorarioAlimenticio;
import nutricionista_g52.vistas.EditarPacienteView;
import nutricionista_g52.vistas.MenuPrincipalView;
import nutricionista_g52.vistas.NuevoPacienteView;
import nutricionista_g52.vistas.PesoView;
import nutricionista_g52.vistas.excepciones.CampoVacioException;
import nutricionista_g52.vistas.excepciones.RangoNumericoException;

/**
 *
 * @author Matías Pacheco
 */
public class DietaComidasView extends javax.swing.JInternalFrame {
    private DefaultTableModel modeloTab;
    private Dieta_ComidaData dieComiData;
    private Dieta dieta;
    
    /**
     * Creates new form PacientesView
     */
    public DietaComidasView() {
        initComponents();
    }
    
    public DietaComidasView(Dieta dieta){
        initComponents();
        this.modeloTab = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
        };
        this.dieComiData = new Dieta_ComidaData();
        this.dieta = dieta;
        porDefecto();
    }
    
//---------- Por Defecto ----------
    private void porDefecto(){
        llenarDatosDeLaDieta();
        listarJComboOrdenarPor();
        listarJComboBuscarPorCalorias();
        agregarColumnasALaTabla();
        deshabilitarElReordenamientoDeColumnas();
        seleccionarJCheckBoxDeLosHorarios(6, true);
        habilitarComponentes(false);
        llenarTablaPorDefecto();
    }
    
    private void llenarDatosDeLaDieta(){
        jLabNombreDieta.setText(dieta.getNombre());
        jLabNombrePaciente.setText(dieta.getPaciente().getApellido()+" "+dieta.getPaciente().getNombre());
    }
    
    private void listarJComboOrdenarPor(){
        jComBoOrdenarPor.addItem("POR NOMBRE");
        jComBoOrdenarPor.addItem("POR CÓDIGO");
        jComBoOrdenarPor.addItem("POR CALORIAS");
        jComBoOrdenarPor.addItem("POR HORARIO");
    }
    
    private void listarJComboBuscarPorCalorias(){
        jComBoBuscarPorCalorias.addItem("POR APROXIMACIÓN DE CALORÍAS");
        jComBoBuscarPorCalorias.addItem("POR MAYOR CANTIDAD DE CALORÍAS");
        jComBoBuscarPorCalorias.addItem("POR MENOR CANTIDAD DE CALORÍAS");
    }
    
    private void agregarColumnasALaTabla(){
        modeloTab.addColumn("CÓDIGO");
        modeloTab.addColumn("COMIDA");
        modeloTab.addColumn("DETALLE");
        modeloTab.addColumn("CALORÍAS");
        modeloTab.addColumn("HORARIO");
        modeloTab.addColumn("PORCIÓN");
        
        
        jTabConsultasComidas.setModel(modeloTab);
    }
    
    private void deshabilitarElReordenamientoDeColumnas(){
        jTabConsultasComidas.getTableHeader().setReorderingAllowed(false);
    }
    
    private void llenarTablaPorDefecto(){
        vaciarFilasDeLaTabla();
        llenarFilasDeLaTabla(dieComiData.listarComidasDeDietaYDieta_ComidaPorIdPacienteSinHorariosNoSeleccionadosOrdenadoAscPor(
                dieta.getPaciente().getIdPaciente(), noBuscarHorarios(), ordenarFilasPorCalorias()));
    }
    
    private void llenarTablaPersonalizado(String calorias){
        vaciarFilasDeLaTabla();
        buscarPorCalorias(calorias);
    }
    
    private void seleccionarJCheckBoxDeLosHorarios(int opcion, boolean habilitado){
        switch(opcion){
            case 1:{ jCheBoDesayuno.setSelected(habilitado); break;}
            case 2:{ jCheBoAlmuerzo.setSelected(habilitado); break; }
            case 3:{ jCheBoMerienda.setSelected(habilitado); break; }
            case 4:{ jCheBoCena.setSelected(habilitado); break; }
            case 5:{ jCheBoSnack.setSelected(habilitado); break; }
            case 6:{
                jCheBoDesayuno.setSelected(habilitado);
                jCheBoAlmuerzo.setSelected(habilitado);
                jCheBoMerienda.setSelected(habilitado);
                jCheBoCena.setSelected(habilitado);
                jCheBoSnack.setSelected(habilitado);
                break;
            }
        }
    }
    
    private void habilitarComponentes(boolean habilitado){
        jCheBoTodos.setEnabled(habilitado);
    }
    
//---------- Listar, Ordenar, Vaciar -----------
    private void llenarFilasDeLaTabla(List<Object> registro){
        Object[] arreglo = null;
        
        for(Object obj :  registro){
            if(obj instanceof Comida){
                arreglo = new Object[6];
                Comida comida = (Comida) obj;
                arreglo[0] = comida.getIdComida(); arreglo[1] = comida.getNombre(); arreglo[2] = comida.getDetalle();
                arreglo[3] = comida.getCantCalorias();
           } else {
                Dieta_Comida dietaComida = (Dieta_Comida) obj;
                arreglo[4] = dietaComida.getHorario(); arreglo[5] = dietaComida.getPorcion();
                modeloTab.addRow(arreglo);
            }
        }
    }
    
    private String ordenarFilasPorCalorias(){
        String ordenarPor = null;
        switch(jComBoOrdenarPor.getSelectedIndex()){
            case 0:{ ordenarPor = "nombre"; break; }
            case 1:{ ordenarPor = "comida.idComida"; break; }
            case 2:{ ordenarPor = "cantCalorias"; break; }
            case 3:{ ordenarPor = "horario"; break; }
        }
        
        return ordenarPor;
    }
    
    private void buscarPorCalorias(String calorias){
        switch(jComBoBuscarPorCalorias.getSelectedIndex()){
            case 0:{ llenarFilasDeLaTabla(dieComiData.
                    listarComidasDeDietaYDieta_ComidaPorAproximacionDeCaloriasPorIdPacienteSinHorariosNoSeleccionadosOrdenadoAscPor(
                            dieta.getPaciente().getIdPaciente(), calorias+"%", noBuscarHorarios(),ordenarFilasPorCalorias())); break; }
            case 1:{ llenarFilasDeLaTabla(dieComiData.listarComidasDeDietaYDieta_ComidaConMayorCantidadDeCaloriasPorIdPacienteSinHorariosNoSeleccionadosOrdenadoAscPor(
                            dieta.getPaciente().getIdPaciente(), Integer.valueOf(calorias), noBuscarHorarios(), 
                            ordenarFilasPorCalorias())); break; }
            case 2:{ llenarFilasDeLaTabla(dieComiData.
                    listarComidasDeDietaYDieta_ComidaConMenorCantidadDeCaloriasPorIdPacienteSinHorariosNoSeleccionadosOrdenadoAscPor(
                            dieta.getPaciente().getIdPaciente(), Integer.parseInt(calorias), noBuscarHorarios(), 
                            ordenarFilasPorCalorias())); break; }
        }
    }
    
    private String noBuscarHorarios(){
        String noBuscar = "";
        
        if(!jCheBoDesayuno.isSelected()){
            noBuscar+= " AND horario NOT LIKE 'DESAYUNO'";
        }
        if(!jCheBoAlmuerzo.isSelected()){
            noBuscar+= " AND horario NOT LIKE 'ALMUERZO'";
        }
        if(!jCheBoMerienda.isSelected()){
            noBuscar+= " AND horario NOT LIKE 'MERIENDA'";
        }
        if(!jCheBoCena.isSelected()){
            noBuscar+= " AND horario NOT LIKE 'CENA'";
        }
        if(!jCheBoSnack.isSelected()){
            noBuscar+= " AND horario NOT LIKE 'SNACK'";
        }
        
        return noBuscar;
    }
    
    private void vaciarFilasDeLaTabla(){
        modeloTab.setRowCount(0);
    }
    
    private void isChekHorarioDesactivado(){
        if(!jCheBoDesayuno.isSelected() || !jCheBoAlmuerzo.isSelected() || !jCheBoMerienda.isSelected() || !jCheBoCena.isSelected() 
                || !jCheBoSnack.isSelected()){
            habilitarComponentes(true);
            jCheBoTodos.setSelected(false);
        } else {
            habilitarComponentes(false);
            jCheBoTodos.setSelected(true);
        }
    }
    
    private Dieta_Comida traerDietaComida(){
        Dieta_Comida dietaComida = dieComiData.buscarDieta_Comida(seleccionarNombreDeComidaEnTabla(), this.dieta.getPaciente().getDni(), 
                seleccionarHorarioEnTabla());
        return dietaComida;
    }
    
    private String seleccionarNombreDeComidaEnTabla(){
        String nombre = (String) jTabConsultasComidas.getValueAt(jTabConsultasComidas.getSelectedRow(), 1);
        return nombre;
    }
    
    private HorarioAlimenticio seleccionarHorarioEnTabla(){
        HorarioAlimenticio horario = (HorarioAlimenticio) jTabConsultasComidas.getValueAt(jTabConsultasComidas.getSelectedRow(), 4);
        return horario;
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
        jButEditar = new javax.swing.JButton();
        jButEliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jComBoBuscarPorCalorias = new javax.swing.JComboBox<>();
        jComBoOrdenarPor = new javax.swing.JComboBox<>();
        jButLimpiar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTexFiBuscar = new javax.swing.JTextField();
        jCheBoDesayuno = new javax.swing.JCheckBox();
        jCheBoAlmuerzo = new javax.swing.JCheckBox();
        jCheBoMerienda = new javax.swing.JCheckBox();
        jCheBoCena = new javax.swing.JCheckBox();
        jCheBoSnack = new javax.swing.JCheckBox();
        jLabJTFBuscar = new javax.swing.JLabel();
        jCheBoTodos = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabNombreDieta = new javax.swing.JLabel();
        jLabNombrePaciente = new javax.swing.JLabel();

        jTabConsultasComidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
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
        jButAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButAgregarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276)
                .addComponent(jButSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButSalir)
                    .addComponent(jButAgregar)
                    .addComponent(jButEditar)
                    .addComponent(jButEliminar))
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COMIDAS");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("registro por selección");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(206, 206, 206))
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

        jButLimpiar.setText("Limpiar");
        jButLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButLimpiarActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTexFiBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTexFiBuscarKeyReleased(evt);
            }
        });

        jCheBoDesayuno.setText("Desayuno");
        jCheBoDesayuno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoDesayunoActionPerformed(evt);
            }
        });

        jCheBoAlmuerzo.setText("Almuerzo");
        jCheBoAlmuerzo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoAlmuerzoActionPerformed(evt);
            }
        });

        jCheBoMerienda.setText("Merienda");
        jCheBoMerienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoMeriendaActionPerformed(evt);
            }
        });

        jCheBoCena.setText("Cena");
        jCheBoCena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoCenaActionPerformed(evt);
            }
        });

        jCheBoSnack.setText("Snack");
        jCheBoSnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoSnackActionPerformed(evt);
            }
        });

        jLabJTFBuscar.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N

        jCheBoTodos.setText("Todos");
        jCheBoTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheBoTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jComBoOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComBoBuscarPorCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheBoTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheBoDesayuno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheBoAlmuerzo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheBoMerienda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheBoCena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheBoSnack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(jButLimpiar)
                            .addComponent(jTexFiBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabJTFBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheBoDesayuno)
                    .addComponent(jCheBoAlmuerzo)
                    .addComponent(jCheBoMerienda)
                    .addComponent(jCheBoCena)
                    .addComponent(jCheBoSnack)
                    .addComponent(jCheBoTodos))
                .addGap(6, 6, 6))
        );

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));

        jLabNombreDieta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabNombreDieta.setForeground(new java.awt.Color(255, 255, 255));
        jLabNombreDieta.setText("Nombre Dieta");

        jLabNombrePaciente.setBackground(new java.awt.Color(204, 204, 204));
        jLabNombrePaciente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabNombrePaciente.setForeground(new java.awt.Color(255, 255, 255));
        jLabNombrePaciente.setText("Nombre Paciente");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabNombreDieta)
                .addGap(12, 12, 12)
                .addComponent(jLabNombrePaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabNombreDieta)
                    .addComponent(jLabNombrePaciente))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void jButAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButAgregarActionPerformed
        // TODO add your handling code here:
        
        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 4){
            ConsultasComidasView conComiV = new ConsultasComidasView(dieta);
            conComiV.setVisible(true);
            
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - conComiV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - conComiV.getHeight()) / 2;
        
            conComiV.setLocation(x, y);
            
            MenuPrincipalView.jDesPaEscritorio.add(conComiV);
            
            conComiV.toFront();
        }
    }//GEN-LAST:event_jButAgregarActionPerformed

    private void jButEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButEditarActionPerformed
        // TODO add your handling code here:
        if(MenuPrincipalView.jDesPaEscritorio.getComponentCount() < 4){
            if(jTabConsultasComidas.getSelectedRow() == -1){
                try{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una comida de la dieta en la tabla", "  Mensaje", 1);
                    return;
                } catch(HeadlessException he){
                    System.err.println(he.getMessage());
                }
            }
            
            EditarDietaComidasView ediDieComiV = new EditarDietaComidasView(traerDietaComida());
            ediDieComiV.setVisible(true);
            
            int x = (MenuPrincipalView.jDesPaEscritorio.getWidth() - ediDieComiV.getWidth()) / 2;
            int y = (MenuPrincipalView.jDesPaEscritorio.getHeight() - ediDieComiV.getHeight()) / 2;
        
            ediDieComiV.setLocation(x, y);
            
            MenuPrincipalView.jDesPaEscritorio.add(ediDieComiV);
            
            ediDieComiV.toFront();
        }
    }//GEN-LAST:event_jButEditarActionPerformed

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
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComBoOrdenarPorActionPerformed

    private void jComBoBuscarPorCaloriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBoBuscarPorCaloriasActionPerformed
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
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(Exception e){
            e.printStackTrace();
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

    private void jCheBoDesayunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoDesayunoActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jCheBoAlmuerzo.isSelected() || jCheBoMerienda.isSelected() || jCheBoCena.isSelected() || jCheBoSnack.isSelected()){
                isChekHorarioDesactivado();
                String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
                int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);

                llenarTablaPersonalizado(caloriasCad);
            } else {
                JOptionPane.showMessageDialog(null, "No puede quedar check de horarios sin seleccionar", "  Mensaje", 1);
                seleccionarJCheckBoxDeLosHorarios(1, true);
            }
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheBoDesayunoActionPerformed

    private void jCheBoAlmuerzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoAlmuerzoActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jCheBoDesayuno.isSelected() || jCheBoMerienda.isSelected() || jCheBoCena.isSelected() || jCheBoSnack.isSelected()){
                isChekHorarioDesactivado();
                String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
                int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);

                llenarTablaPersonalizado(caloriasCad);
            } else {
                JOptionPane.showMessageDialog(null, "No puede quedar check de horarios sin seleccionar", "  Mensaje", 1);
                seleccionarJCheckBoxDeLosHorarios(2, true);
            }
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheBoAlmuerzoActionPerformed

    private void jCheBoMeriendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoMeriendaActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jCheBoDesayuno.isSelected() || jCheBoAlmuerzo.isSelected() || jCheBoCena.isSelected() || jCheBoSnack.isSelected()){
                isChekHorarioDesactivado();
                String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
                int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);

                llenarTablaPersonalizado(caloriasCad);
            } else {
                JOptionPane.showMessageDialog(null, "No puede quedar check de horarios sin seleccionar", "  Mensaje", 1);
                seleccionarJCheckBoxDeLosHorarios(3, true);
            }
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheBoMeriendaActionPerformed

    private void jCheBoCenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoCenaActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jCheBoDesayuno.isSelected() || jCheBoAlmuerzo.isSelected() || jCheBoMerienda.isSelected() || jCheBoSnack.isSelected()){
                isChekHorarioDesactivado();
                String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
                int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);

                llenarTablaPersonalizado(caloriasCad);
            } else {
                JOptionPane.showMessageDialog(null, "No puede quedar check de horarios sin seleccionar", "  Mensaje", 1);
                seleccionarJCheckBoxDeLosHorarios(4, true);
            }
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheBoCenaActionPerformed

    private void jCheBoSnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoSnackActionPerformed
        // TODO add your handling code here:
        
        try{
            if(jCheBoDesayuno.isSelected() || jCheBoAlmuerzo.isSelected() || jCheBoMerienda.isSelected() || jCheBoCena.isSelected()){
                isChekHorarioDesactivado();
                String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
                int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);

                llenarTablaPersonalizado(caloriasCad);
            } else {
                JOptionPane.showMessageDialog(null, "No puede quedar check de horarios sin seleccionar", "  Mensaje", 1);
                seleccionarJCheckBoxDeLosHorarios(5, true);
            }
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheBoSnackActionPerformed

    private void jCheBoTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheBoTodosActionPerformed
        // TODO add your handling code here:
        
        try{
            seleccionarJCheckBoxDeLosHorarios(6, true);
            isChekHorarioDesactivado();
            
            String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
            int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);

            llenarTablaPersonalizado(caloriasCad);
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCheBoTodosActionPerformed

    private void jButEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButEliminarActionPerformed
        // TODO add your handling code here:
        try{
            if(jTabConsultasComidas.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una comida de la dieta en la tabla", "  Mensaje", 1);
                return;
            }
            
            int entrada = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la comida de la dieta?", "  Eliminar "
                    + "Comida de Dieta", 0);
            System.out.println(entrada);
            if(entrada == 0){
                dieComiData.borrarComida_Dieta(traerDietaComida().getIdDietaComida());
            }
        
            String caloriasCad = jTexFiBuscar.getText(); excepcionCampoVacio(caloriasCad);
            int calorias = (int) Long.parseLong(caloriasCad); excepcionRangoNumerico(calorias);
            
            llenarTablaPersonalizado(caloriasCad);
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(CampoVacioException cve){
            llenarTablaPorDefecto();
        } catch(NumberFormatException nfe){
            //Capturo está excepción para evitar que intente listar una cadena de caracteres
        } catch(RangoNumericoException rne){
            //Capturo está excepción para evitar que intente buscar números superiores o inferiores
        } catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButAgregar;
    private javax.swing.JButton jButEditar;
    private javax.swing.JButton jButEliminar;
    private javax.swing.JButton jButLimpiar;
    private javax.swing.JButton jButSalir;
    private javax.swing.JCheckBox jCheBoAlmuerzo;
    private javax.swing.JCheckBox jCheBoCena;
    private javax.swing.JCheckBox jCheBoDesayuno;
    private javax.swing.JCheckBox jCheBoMerienda;
    private javax.swing.JCheckBox jCheBoSnack;
    private javax.swing.JCheckBox jCheBoTodos;
    private javax.swing.JComboBox<String> jComBoBuscarPorCalorias;
    private javax.swing.JComboBox<String> jComBoOrdenarPor;
    private javax.swing.JLabel jLabJTFBuscar;
    private javax.swing.JLabel jLabNombreDieta;
    private javax.swing.JLabel jLabNombrePaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTabConsultasComidas;
    private javax.swing.JTextField jTexFiBuscar;
    // End of variables declaration//GEN-END:variables
}
