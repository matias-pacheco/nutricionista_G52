/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pachecomatias;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.swing.JOptionPane;
import nutricionista_g52.accesoADatos.ComidaData;
import nutricionista_g52.accesoADatos.DietaData;
import nutricionista_g52.accesoADatos.Dieta_ComidaData;
import nutricionista_g52.accesoADatos.Historial_PesoData;
import nutricionista_g52.accesoADatos.PacienteData;
import nutricionista_g52.entidades.Comida;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.entidades.Dieta_Comida;
import nutricionista_g52.entidades.Historial_Peso;
import nutricionista_g52.entidades.Paciente;
import nutricionista_g52.entidades.enumeraciones.HorarioAlimenticio;
import nutricionista_g52.vistas.enumeraciones.NombreDeDieta;

/**
 *
 * @author Matías Pacheco
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//---------- P R U E B A S   C R U D   P A C I E N T E ----------
        PacienteData pacData = new PacienteData();

//----- CREATE paciente -----
//--- pac1 ----
//        Paciente pac1 = new Paciente(10222333, "Peréz", "Juan", "Calle 123", "11 20023003", true);
//        
//        pacData.guardarPaciente(pac1);

//--- pac2 ---
//        Paciente pac2 = new Paciente(30444555, "Gambini", "Jorge", "Calle ALta 333", "11 40045005", true);
//        
//        pacData.guardarPaciente(pac2);

        
//----- READ paciente -----
//--- pac1 ---
//        Paciente pac1Lectura = pacData.buscarPaciente(1);
//        System.out.println(pac1Lectura);
        
//--- pac2 ---
//        Paciente pac2Lectura = pacData.buscarPaciente(2);
//        System.out.println(pacData.buscarPaciente(pac2Lectura.getIdPaciente()));


//----- UPDATE paciente -----
//--- pac1 ---
//        pac1Lectura.setDni(20333444);
//        pac1Lectura.setApellido("Giraldo");
//        pac1Lectura.setNombre("Romualdo");
//        pac1Lectura.setDomicilio("Calle 321");
//        pac1Lectura.setTelefono("11 30032002");
//        
//        pacData.modificarPaciente(pac1Lectura);
        
//--- pac2 ---
//        pac2Lectura.setDni(40666777);
//        pac2Lectura.setApellido("Lang");
//        pac2Lectura.setNombre("Rodrigo");
//        pac2Lectura.setDomicilio("Calle Baja 321");
//        pac2Lectura.setTelefono("11 50054004");
//
//        pacData.modificarPaciente(pac2Lectura);
        
        
//----- READ paciente -----
////--- pac1 ---
//        System.out.println(pacData.buscarPaciente(1));

////--- pac2 ---
//        System.out.println(pacData.buscarPaciente(pac2Lectura.getIdPaciente()));


//----- DELETE(lógico) -----
////--- pac1 ---
//        pacData.borrarPaciente(pac1Lectura.getIdPaciente());

////--- pac2 ---
//        pacData.borrarPaciente(pac2Lectura.getIdPaciente());

        
//----- READ paciente -----
//--- pac1 ---
//        System.out.println(pacData.buscarPaciente(pac1Lectura.getIdPaciente()));

//--- pac2 ---
//        System.out.println(pacData.buscarPaciente(pac2Lectura.getIdPaciente()));


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   H I S T O R I A L _ P E S O ----------
        Historial_PesoData hisPesoData = new Historial_PesoData();
        
//----- CREATE historial_peso -----
//--- hisPeso1 ---
////    --- paciente ---
//        pacData.restaurarPaciente(1); //- UPDATE(retaurar paciente) paciente -
//        Paciente pac1Lectura2 = pacData.buscarPaciente(1);
////    ----------------
//        Historial_Peso hisPeso1 = new Historial_Peso(pac1Lectura2, LocalDate.of(2023, Month.OCTOBER, 04), 71.5);
//        
//        hisPesoData.guardarHistorialPeso(hisPeso1);

//--- hisPeso2 ---
////    --- paciente ---
//        pacData.restaurarPaciente(2);
//        Paciente pac2Lectura2 = pacData.buscarPaciente(2);
////    ----------------
//        Historial_Peso hisPeso2 = new Historial_Peso(pac2Lectura2, LocalDate.now(), 80);
//        
//        hisPesoData.guardarHistorialPeso(hisPeso2);

//--- hisPeso3 ---
//    --- paciente ---
//        Paciente pac1Lectura3 = pacData.buscarPaciente(1);
////    ----------------        
//        Historial_Peso hisPeso3 = new Historial_Peso(pac1Lectura3, LocalDate.of(2022, Month.APRIL, 17), 58.8);
//        
//        hisPesoData.guardarHistorial_Peso(hisPeso3);
        
//--- hisPeso4 ---
////    --- paciente ---
//        Paciente pac2Lectura3 = pacData.buscarPaciente(2);
////    ----------------
//        Historial_Peso hisPeso4 = new Historial_Peso(pac2Lectura3, LocalDate.of(2023, Month.SEPTEMBER, 7), 78);
//        
//        hisPesoData.guardarHistorial_Peso(hisPeso4);
        
//--- hisPeso5 ---
////    --- paciente ---
//        Paciente pac2Lectura4 = pacData.buscarPaciente(2);
////    ----------------
//        Historial_Peso hisPeso5 = new Historial_Peso(pac2Lectura4, LocalDate.of(2023, Month.OCTOBER, 7), 82);
//        
//        hisPesoData.guardarHistorial_Peso(hisPeso5);

//--- hisPeso6 ---
////    --- paciente ---
//        Paciente pac3 = new Paciente(70888999, "Mujica", "Sergio", "Calle Pobre 123", "11 90092002", true);
//        pacData.guardarPaciente(pac3);
//        Paciente pac3Lectura1 = pacData.buscarPaciente(4);
////    ----------------
//        Historial_Peso hisPeso6 = new Historial_Peso(pac3Lectura1, LocalDate.of(2023, Month.OCTOBER, 7), 84);
//        
//        hisPesoData.guardarHistorial_Peso(hisPeso6);


//----- READ historial_peso -----
////--- hisPeso1 ---
//        System.out.println(hisPesoData.buscarHistorial_PesoActual(2));
//        
////--- hisPeso2 ---
//        System.out.println(hisPesoData.buscarHistorial_PesoActual(1));
        
        
//----- READ listar historial_peso -----
////--- registro1 ---
////      --- paciente ---
//        Paciente pac1Lectura4 = pacData.buscarPaciente(1);
////      ----------------
//        List<Historial_Peso> registro = hisPesoData.listarHistorial_Peso(pac1Lectura4.getIdPaciente());
        
//        for(Historial_Peso hisPeso : registro){
//            System.out.println(hisPeso);
//        }
        
//----- UPDATE historial_peso -----
//--- hisPeso1 ---
//        Historial_Peso hisPesoLectura1 = registro.get(1);
//        
//        System.out.println(hisPesoLectura1);
//        
//        hisPesoLectura1.setFecha(LocalDate.of(2023, Month.OCTOBER, 3));
//        hisPesoLectura1.setPeso(70);
//        
//        hisPesoData.modificarHistorial_Peso(hisPesoLectura1);
//        
//        System.out.println(registro.get(1));

//----- READ listar historial_peso -----
//--- registro2 ---
////    --- paciente1 ---
//        Paciente paciente1Lectura5 = pacData.buscarPaciente(1);
////    -----------------
//        List<Historial_Peso> registro2 = hisPesoData.listarHistorial_Peso(paciente1Lectura5.getIdPaciente());
//        
//        for(Historial_Peso hisPeso : registro2){
//            System.out.println(hisPeso);
//        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   D I E T A ----------
        DietaData dieData = new DietaData();
        
//----- CREATE dieta -----
//--- die1 ---
////    --- paciente ---
//        Paciente pac1Lectura6 = pacData.buscarPaciente(1);
////    ----------------
//        Dieta die1 = new Dieta("Mediterranea", pac1Lectura6, LocalDate.now(), 72, LocalDate.of(2023, 
//                Month.DECEMBER, 10), 80);
//        
//        dieData.guardarDieta(die1);
        
//--- die2 ---
////    --- paciente ---
//        Paciente pac2Lectura5 = pacData.buscarPaciente(2);
////    ----------------
//        Dieta die2 = new Dieta("Vegetariana", pac2Lectura5, LocalDate.now(), 82, LocalDate.of(2024, 
//                Month.JANUARY, 10), 74);
//        
//        dieData.guardarDieta(die2);

//--- die3 ---
////    --- paciente ---
//        Paciente pac3Lectura2 = pacData.buscarPaciente(4);
////    ----------------
//        Dieta die3 = new Dieta("Proteica", pac3Lectura2, LocalDate.now(), 90, LocalDate.of(2023, 
//                Month.DECEMBER, 8), 80);
//        
//        dieData.guardarDieta(die3);

//--- die4 ---
////    --- paciente ---
//        Paciente pac3Lectura3 = pacData.buscarPaciente(4);
////    ----------------
//        Dieta die4 = new Dieta("Proteica", pac3Lectura3, LocalDate.of(2023, Month.DECEMBER, 9), 81, 
//                LocalDate.of(2024, Month.APRIL, 17), 73);
//        
//        dieData.guardarDieta(die4);

//----- READ dieta -----
//--- die1 ---
////    --- paciente ---
//        Paciente pac1Lectura7 = pacData.buscarPaciente(1);
////    ----------------
//        System.out.println(dieData.buscarDietaPorIdPaciente(pac1Lectura7.getIdPaciente()));

//----- UPDATE dieta -----
//--- die1 ---
//        Dieta die1Lectura1 = dieData.buscarDietaPorIdPaciente(1);
        
//        die1Lectura1.setFechaInicial(LocalDate.of(2022, Month.OCTOBER, 7));
//        die1Lectura1.setPesoInicial(65.5);
//        die1Lectura1.setFechaFinal(LocalDate.of(2023, Month.JANUARY, 7));
//        die1Lectura1.setPesoFinal(75.5);
//        
//        dieData.modificarDieta(die1Lectura1);

//----- READ dieta -----
//--- die2 ---
////    --- paciente ---
//        Paciente pac1Lectura8 = pacData.buscarPaciente(1);
////    ----------------
//        System.out.println(dieData.buscarDietaPorIdPaciente(pac1Lectura8.getIdPaciente()));

//----- UPDATE dieta -----
//--- die2 ---
//        Dieta die1Lectura2 = dieData.buscarDieta(1);
//        
//        die1Lectura2.setFechaInicial(LocalDate.of(2025, Month.AUGUST, 17));
//        
//        dieData.modificarDieta(die1Lectura2);
        
//----- UPDATE dieta -----
//--- die3 ---
//        Dieta die1Lectura3 = dieData.buscarDietaPorIdPaciente(1);
//        
//        Dieta dieta1Aux = new Dieta("Paleo", die1Lectura3.getPaciente(), LocalDate.of(2023, Month.JANUARY, 8), 
//                70, LocalDate.of(2023, Month.AUGUST, 7), 76);
//        dieta1Aux.setIdDieta(18);
//        
//        dieData.modificarDieta(dieta1Aux);

//----- INSERT dieta -----
//--- die4 ---
////    --- paciente ---
//        Paciente pac4 = new Paciente(12555777, "Lata", "Ezequiel", "Calle Cien 123", "11 3456 3455", true);
//        
//        pacData.guardarPaciente(pac4);
////    ----------------
//        Dieta die4 = new Dieta("Hipocalórica", pac4, LocalDate.of(2022, Month.SEPTEMBER, 21), 65, 
//                LocalDate.of(2023, Month.MARCH, 23), 70);
//        
//        dieData.guardarDieta(die4);

//----- READ lista paciente dieta terminada -----
//--- registro1 ---
//        List<Object> registro = dieData.listarPacientesDietaTerminada();
//        String esp = "          ";
//        
//        for(Object obj : registro){
//            if(obj instanceof Paciente){
//                Paciente paciente = (Paciente)obj;
//                
//                System.out.println(esp+paciente+"\n");
//            } else {
//                Dieta dieta = (Dieta)obj;
//                
//                System.out.println(dieta);
//            }
//        }

//----- READ lista paciente dieta terminada B -----
//--- registro 2 ---
//    List<Object> registro2 = dieData.listarPacientesDietaTerminadaB();
//        String esp2 = "          ";
//        
//        for(Object obj : registro2){
//            if(obj instanceof Paciente){
//                Paciente paciente = (Paciente)obj;
//                
//                System.out.println(esp2+paciente+"\n");
//            } else {
//                Dieta dieta = (Dieta)obj;
//                
//                System.out.println(dieta);
//            }
//        }

//----- READ lista pacientes dieta vigente -----
////--- registro 3 ---
//        List<Object> registro3 = dieData.listarPacientesDietaVigente();
//        String esp = "          ";
//        
//        for(Object obj : registro3){
//            if(obj instanceof Paciente){
//                Paciente paciente = (Paciente)obj;
//                
//                System.out.println(esp+paciente+"\n");
//            } else {
//                Dieta dieta = (Dieta)obj;
//                
//                System.out.println("fechaFinal: "+dieta.getFechaFinal()+"\n-------------------------------");
//            }
//        }

//----- READ listar pacientes que no alcanzaron peso buscado -----
//--- registro 4 ---
//    --- paciente ---
//        Paciente pac4Lectura1 = pacData.buscarPaciente(5);
////    --------------
////    --- historial peso ---
//        Historial_Peso hisPesoPac4 = new Historial_Peso(pac4Lectura1, LocalDate.of(2022, Month.SEPTEMBER, 21), 65);
//        Historial_Peso hisPesoPac4B = new Historial_Peso(pac4Lectura1, LocalDate.of(2023, Month.JANUARY, 21), 67);
//        Historial_Peso hisPesoPac4C = new Historial_Peso(pac4Lectura1, LocalDate.of(2023, Month.MARCH, 23), 68.5);
//        
//        hisPesoData.guardarHistorial_Peso(hisPesoPac4);
//        hisPesoData.guardarHistorial_Peso(hisPesoPac4B);
//        hisPesoData.guardarHistorial_Peso(hisPesoPac4C);
//    ----------------------
//        List<Object> registro = dieData.listarPacientesQueNoAlcanzaronPesoBuscado();
//        String esp = "          ";
//        
//        for(Object obj : registro){
//            if(obj instanceof Paciente){
//                Paciente paciente = (Paciente)obj;
//                
//                System.out.println(esp+paciente+"\n");
//            } else if(obj instanceof Dieta){
//                Dieta dieta = (Dieta)obj;
//                
//                System.out.println(dieta);
//            } else {
//                Historial_Peso historialPeso = (Historial_Peso)obj;
//                
//                System.out.println(historialPeso+"\n-----------------------------------------------");
//            }
//        }

//--- registro 5 ---
//        List<Object> registro2 = dieData.listarPacientesQueNoAlcanzaronPesoBuscado();
//        String esp = "          ";
//        
//        for(Object obj : registro2){
//            if(obj instanceof Paciente){
//                Paciente paciente = (Paciente)obj;
//                
//                System.out.println(esp+paciente+"\n");
//            } else if(obj instanceof Dieta){
//                Dieta dieta = (Dieta)obj;
//                
//                System.out.println("fechaFinal: "+dieta.getFechaFinal()+"\nPesoFinal: "+dieta.getPesoFinal()+"\n");
//            } else {
//                Historial_Peso historialPeso = (Historial_Peso)obj;
//                
//                System.out.println("fecha: "+historialPeso.getFecha()+"\npeso: "+historialPeso.getPeso()
//                        +"\n-----------------------------------------------");
//            }
//        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   C O M I D A ----------
        ComidaData comiData = new ComidaData();
        
//----- CREATE comida -----
//--- com1 ---
//        Comida com1 = new Comida("Ensalada Natural", "Sin sal y aceite", 50, true);
//        
//        comiData.guardarComida(com1);
        
//----- READ comida -----
//--- com1 ---
//        Comida com1Lec1 = comiData.buscarComidaPorId(2);
//        String esp = "        ";
//        
//        System.out.println(esp+com1Lec1);

//----- READ comida por nombre -----
//--- com1 ---
//        Comida com1Lec2 = comiData.buscarComidaPorNombre("Pechuga");
//        String esp = "        ";
//        
//        System.out.println(esp+com1Lec2);

//----- UPDATE comida -----
//--- com1 ---
//        Comida com1Lec3 = comiData.buscarComidaPorId(2);
//        
//        com1Lec3.setNombre("Asado");
//        com1Lec3.setDetalle("Al horno");
//        com1Lec3.setCantCalorias(1500);
//        
//        comiData.modificarComida(com1Lec3);
        
//--- com1 ---
//        Comida com1Lec4 = comiData.buscarComidaPorId(1);
//        
//        com1Lec4.setNombre("Pechuga");
//        com1Lec4.setDetalle("Al horno");
//        com1Lec4.setCantCalorias(400);
//        
//        comiData.modificarComida(com1Lec4);

//----- DELETE(lógico) paciente -----
//--- com1 ---
//        comiData.borrarComida(comiData.buscarComidaPorId(1).getIdComida());

//----- UPDATE(restaurar) comida -----
//--- com1 ---
//        comiData.restaurarComida(1);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   P A C I E N T E ----------
//----- READ paciente (por DNI) -----
//--- pac1 ---
//        String esp = "          ";
//    
//        System.out.println(esp+pacData.buscarpacientePorDni(20333444));


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   C O M I D A ----------
//----- READ listar comidas con cantidad de calorias menor al seleccionado -----
//--- list1 ---
//        List<Comida> registro = comiData.listarComidasConMenorCantidadCalorias(401);
//        String esp = "        ";
//        
//        for(Comida comida : registro){
//            System.out.println(esp+comida+"\n");
//        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   D I E T A _ C O M I D A ----------
        Dieta_ComidaData dieComiData = new Dieta_ComidaData();
        
//----- CREATE dieta_comida -----
//--- dieCom1 ---
//    --- paciente ---
//        Paciente paciente = pacData.buscarpacientePorDni(40666777);
////    ----------------
////    --- comida ---
//        Comida comida = comiData.buscarComidaPorNombre("Ensalada Natural");
////    --------------
////    --- dieta ---
//        Dieta dieta = dieData.buscarDietaPorIdPaciente(paciente.getIdPaciente());
////    -------------
//        Dieta_Comida dieCom1 = new Dieta_Comida(comida, dieta, HorarioAlimenticio.DESAYUNO, 200);
//        
//        dieComiData.guardarDieta_Comida(dieCom1);

//--- dieCom2 ---
//    --- paciente ---
//        Paciente paciente1 = pacData.buscarpacientePorDni(40666777);
////    ----------------
////    --- comida ---
//        Comida comida1 = new Comida("Carne con Papas", "Hervido", 800, true);
//        comiData.guardarComida(comida1);
////    --------------
////    --- dieta ---
//        Dieta dieta1 = dieData.buscarDietaPorIdPaciente(paciente1.getIdPaciente());
////    -------------
//        Dieta_Comida dieCom2 = new Dieta_Comida(comida1, dieta1, HorarioAlimenticio.ALMUERZO, 450);
//        
//        dieComiData.guardarDieta_Comida(dieCom2);

//--- dieCom3 ---
//    --- paciente ---
//        Paciente paciente3 = pacData.buscarPaciente(4);
////    ----------------
////    --- comida ---
//        Comida comida2 = comiData.buscarComidaPorNombre("Carne con Papas");
////    --------------
////    --- dieta ---
//        Dieta dieta2 = dieData.buscarDietaPorIdPaciente(paciente3.getIdPaciente());
////    -------------
//        Dieta_Comida dieCom3 = new Dieta_Comida(comida2, dieta2, HorarioAlimenticio.ALMUERZO, 500);
//        
//        dieComiData.guardarDieta_Comida(dieCom3);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   D I E T A ----------
//----- READ dieta por DNI -----
//--- dieta ---
//        System.out.println(dieData.buscarDietaPorDni(40666777));

//----- READ dieta por ID paciente -----   
//--- dieta ---
//        System.out.println(dieData.buscarDietaPorIdPaciente(pacData.buscarpacientePorDni(40666777).getIdPaciente()));

//----- READ dieta por ID dieta -----
//--- dieta ---
//        String esp = "       ";
//        System.out.println(esp+dieData.buscarDieta(60));


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//---------- P R U E B A S   C R U D   D I E T A _ C O M I D A ----------
//----- READ dieta_comida -----
//--- dieComida ---
//        System.out.println(dieComiData.buscarDieta_Comida("Carne con Papas", 40666777, 
//                HorarioAlimenticio.ALMUERZO));

//----- READ dieta_comida B-----
//--- dieComida ---
//        System.out.println(dieComiData.buscarDieta_ComidaB("Carne con Papas", 40666777, 
//                HorarioAlimenticio.ALMUERZO));

//----- UPDATE modificar dieta_comida -----
//--- dieComida1 ---
//    --- comida ---
//        Comida comida = comiData.buscarComidaPorNombre("Carne con Papas");
////    --------------
////    --- dieta ---
//        Dieta dieta = dieData.buscarDietaPorDni(40666777);
////    -------------
//        Dieta_Comida dieComida1 = new Dieta_Comida(comida, dieta, HorarioAlimenticio.ALMUERZO, 400);
//        
//        dieComiData.modificarDieta_Comida(dieComida1);

//----- DELETE borrar comida de dieta -----
//--- dieComida ---
//        Dieta_Comida dieComida = dieComiData.buscarDieta_Comida("Carne con Papas", 40666777, 
//                HorarioAlimenticio.ALMUERZO);
//        
//        dieComiData.borrarComidaDeDieta(dieComida);

//----- INSERT dieta_comida -----
//--- dieComida ---
//    --- comida ---
//        Comida comida = comiData.buscarComidaPorNombre("Carne con Papas");
////    --------------
////    --- dieta ---
//        Dieta dieta = dieData.buscarDietaPorDni(40666777);
////    -------------
//        Dieta_Comida dieComida = new Dieta_Comida(comida, dieta, HorarioAlimenticio.ALMUERZO, 350);
//        
//        dieComiData.guardarDieta_Comida(dieComida);

//----- DELETE dieta_comida -----
////--- dieComida ---
//        dieComiData.borrarComida_Dieta(6);

//----- READ listar comidas de dieta por ID paciente -----
//--- registro1 ---
//        List<Comida> registro1 = dieComiData.listarComidasDeDietaPorIdPaciente(
//                pacData.buscarpacientePorDni(40666777).getIdPaciente());
//        String esp = "        ";
//        
//        for(Comida comi : registro1){
//            System.out.println(esp+comi+"\n");
//        }

//---------- P R U E B A   M É T O D O S   D E   S T R I N G ----------
//----- MÉTODO REPLACE -----
//        System.out.println("Hola    soy          Jo                     r               ge".replace(" ", ""));

        
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//        System.out.println(JOptionPane.showConfirmDialog(null, "", "  Eliminar Paciente", 0));
        
//        for(Paciente pac : pacData.listarPacientesPorApellido("Bu%")){
//            System.out.println(pac);
//        }

//        for(Paciente pac : pacData.listarPacientesPorDni("2%")){
//            System.out.println(pac);
//        }
        
//        double num = 2;
//        System.out.println(num);

//        String ss = null;
//        
//        JOptionPane.showInputDialog(null, "", "", 1);
//        
//        String ejemplo = "H o lá A So  y  JORgee Ö";
//        
//        String ee =ejemplo.replace(" ", "_");
//        
//        System.out.println(ee.toUpperCase());
        
//        NombreDeDieta nn = NombreDeDieta.ATKINS;
//        
//        nn.agregarDieta("h  o  l      a");
//        nn.agregarDieta("h  dd d");
//        nn.agregarDieta("adi     os");
//        
//        int esp = 0;
//        
//        for(int i = 0; i < nn.getDietasAgregadas().size(); i++){
//            String cadena = nn.getDietasAgregadas().get(i);
//            String nue = cadena.replaceAll("\\s+", " ");
//            
//            System.out.println(nue);
//        }
//        
//        Object obj = new Object();
//        
//        obj = 1;
//        
//        int ff = (int)obj;
        
//        System.out.println(nn.getDietasAgregadas().get(0));
//        System.out.println(nn.getDietasAgregadas().get(1));
//        System.out.println(nn.getDietasAgregadas().get(2));

//        Object[] arreglo = new Object[5];
//        
//        arreglo[0] = "hola1";
//        arreglo[1] = "hola2";
//        arreglo[2] = "hola3";
//        arreglo[3] = "hola4";
//        arreglo[4] = "hola5";
//        
//        for(int i = 0; i < arreglo.length; i++){
//            System.out.println(arreglo[i]);
//        }


//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//        for(Object obj : dieData.listarPacientesQueNoAlcanzaronPesoBuscadoPorOrdenAsc("apellido")){
//            System.out.println(obj);
//        }

//        for(Object obj : dieComiData.listarComidasDeDietaYDieta_ComidaPorIdPacienteOrdenadoAscPor(2, "nombre")){
//            System.out.println(obj);
//        }

//        String hola = "";
//        
//        hola+= "adios";
//        
//        System.out.println(hola);

//        Dieta_Comida dietaComida = new Dieta_Comida(comiData.buscarComidaPorId(3), dieData.buscarDieta(60), 
//                HorarioAlimenticio.ALMUERZO, 880);
//        dieComiData.modificarDieta_Comida(dietaComida);
    }
}
