/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pachecomatias;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import nutricionista_g52.accesoADatos.Historial_PesoData;
import nutricionista_g52.accesoADatos.PacienteData;
import nutricionista_g52.entidades.Historial_Peso;
import nutricionista_g52.entidades.Paciente;

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
    }
}
