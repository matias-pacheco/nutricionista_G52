/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pachecomatias;

import nutricionista_g52.accesoADatos.PacienteData;
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
////--- pac1 ----
//        Paciente pac1 = new Paciente(10222333, "Peréz", "Juan", "Calle 123", "11 20023003", true);
//        
//        pacData.guardarPaciente(pac1);

////--- pac2 ---
//        Paciente pac2 = new Paciente(30444555, "Gambini", "Jorge", "Calle ALta 333", "11 40045005", true);
//        
//        pacData.guardarPaciente(pac2);

        
//----- READ paciente -----
////--- pac1 ---
        Paciente pac1Lectura = pacData.buscarPaciente(1);
//        System.out.println(pac1Lectura);
        
////--- pac2 ---
        Paciente pac2Lectura = pacData.buscarPaciente(2);
//        System.out.println(pacData.buscarPaciente(pac2Lectura.getIdPaciente()));


//----- UPDATE paciente -----
////--- pac1 ---
//        pac1Lectura.setDni(20333444);
//        pac1Lectura.setApellido("Giraldo");
//        pac1Lectura.setNombre("Romualdo");
//        pac1Lectura.setDomicilio("Calle 321");
//        pac1Lectura.setTelefono("11 30032002");
//        
//        pacData.modificarPaciente(pac1Lectura);
        
////--- pac2 ---
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
////--- pac1 ---
//        System.out.println(pacData.buscarPaciente(pac1Lectura.getIdPaciente()));
//
////--- pac2 ---
//        System.out.println(pacData.buscarPaciente(pac2Lectura.getIdPaciente()));
    }
}
