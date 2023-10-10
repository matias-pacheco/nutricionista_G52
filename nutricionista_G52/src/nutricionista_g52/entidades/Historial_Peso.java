/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.entidades;

import java.time.LocalDate;

/**
 *
 * @author Matías Pacheco
 */
public class Historial_Peso {
    private int idHistorialPeso;
    private Paciente paciente;
    private LocalDate fecha;
    private double peso;

    public Historial_Peso(int idHistorialPeso, Paciente paciente, LocalDate fecha, double peso) {
        this.idHistorialPeso = idHistorialPeso;
        this.paciente = paciente;
        this.fecha = fecha;
        this.peso = peso;
    }

    public Historial_Peso(Paciente paciente, LocalDate fecha, double peso) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.peso = peso;
    }
    
    public Historial_Peso(){}

    public int getIdHistorialPeso() {
        return idHistorialPeso;
    }
    public void setIdHistorialPeso(int idHistorialPeso) {
        this.idHistorialPeso = idHistorialPeso;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "idHistorialPeso: "+idHistorialPeso+"\npaciente: "+paciente+"\nfecha: "+fecha+"\npeso: "+peso+"\n";
    }
}
