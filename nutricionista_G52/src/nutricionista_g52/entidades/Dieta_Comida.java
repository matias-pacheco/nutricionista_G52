/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.entidades;

import nutricionista_g52.entidades.enumeraciones.HorarioAlimenticio;

/**
 *
 * @author Matías Pacheco
 */
public class Dieta_Comida {
    private int idDietaComida;
    private Comida comida;
    private Dieta dieta;
    private HorarioAlimenticio horario;
    private int porcion;

    public Dieta_Comida(int idDietaComida, Comida comida, Dieta dieta, HorarioAlimenticio horario, int porcion) {
        this.idDietaComida = idDietaComida;
        this.comida = comida;
        this.dieta = dieta;
        this.horario = horario;
        this.porcion = porcion;
    }

    public Dieta_Comida(Comida comida, Dieta dieta, HorarioAlimenticio horario, int porcion) {
        this.comida = comida;
        this.dieta = dieta;
        this.horario = horario;
        this.porcion = porcion;
    }
    
    public Dieta_Comida(){}

    public int getIdDietaComida() {
        return idDietaComida;
    }
    public void setIdDietaComida(int idDietaComida) {
        this.idDietaComida = idDietaComida;
    }

    public Comida getComida() {
        return comida;
    }
    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Dieta getDieta() {
        return dieta;
    }
    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public HorarioAlimenticio getHorario() {
        return horario;
    }
    public void setHorario(HorarioAlimenticio horario) {
        this.horario = horario;
    }
    
    public int getPorcion(){
        return porcion;
    }
    public void setPorcion(int porcion){
        this.porcion = porcion;
    }

    @Override
    public String toString() {
        return "idDietaComida: "+idDietaComida+"\ncomida: "+comida+"\ndieta: "+dieta+"\nhorario: "+horario
                +"\nporcion: "+porcion+"\n";
    }
}
