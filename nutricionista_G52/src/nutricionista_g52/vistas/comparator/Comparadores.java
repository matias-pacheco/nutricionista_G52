/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas.comparator;

import java.util.Comparator;
import nutricionista_g52.entidades.Paciente;

/**
 *
 * @author Matías Pacheco
 */
public class Comparadores {
    public static Comparator<Paciente> ordenarPorApellido = new Comparator<Paciente>(){
        @Override
        public int compare(Paciente p1, Paciente p2){
            return p1.getApellido().compareToIgnoreCase(p2.getApellido());
        }
    };
    
    public static Comparator<Paciente> ordenarPorDni = new Comparator<Paciente>(){
        @Override
        public int compare(Paciente p1, Paciente p2){
            if(p1.getDni() < p2.getDni()){ return -1; }
            else if(p1.getDni() == p2.getDni()){ return 0; }
            else { return 1; }
        }
    };
}
