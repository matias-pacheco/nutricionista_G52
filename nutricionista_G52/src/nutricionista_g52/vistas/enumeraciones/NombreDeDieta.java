/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas.enumeraciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matías Pacheco
 */
public enum NombreDeDieta {
    MEDITERRÁNEA, DE_LA_ZONA, VEGETARIANA, VEGANA, DE_LA_FERTILIDAD, HIPOCALÓRICA, HIPERCALÓRICA, VOLUMÉTRICA, KETO, DETOX, ORNISH, 
    DASH, PALEOLÍTICA, CETOGÉNICA, ATKINS;
    
    private List<String> dietasAgregadas;
    
    NombreDeDieta(){
        this.dietasAgregadas = new ArrayList<>();
    }
    
    public List<String> getDietasAgregadas(){
        return dietasAgregadas;
    }
    private void setDietasAgregadas(List<String> dietasAgregadas){
        this.dietasAgregadas = dietasAgregadas;
    }
    
    public void agregarDieta(String dieta){
//        String remplazo = dieta.replace(" ", "_");
        
        dietasAgregadas.add(dieta.toUpperCase());
    }
}
