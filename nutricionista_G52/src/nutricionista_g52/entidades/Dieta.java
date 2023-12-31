//---comienzo del archivo Dieta.java---
// @author pablo

package nutricionista_g52.entidades;

import java.time.LocalDate;


public class Dieta {
        private int idDieta;
        private String nombre;
        private Paciente paciente;
        private LocalDate fechaInicial;
        private double pesoInicial;
        private LocalDate fechaFinal;
        private double pesoFinal;
        
    //constructores
    //1_vacio
    public Dieta() {
    }
    //2_con_todo
    public Dieta(int idDieta, String nombre, Paciente paciente, LocalDate fechaInicial, double pesoInicial, LocalDate fechaFinal, double pesoFinal) {
        this.idDieta = idDieta;
        this.nombre = nombre;
        this.paciente = paciente;
        this.fechaInicial = fechaInicial;
        this.pesoInicial = pesoInicial;
        this.fechaFinal = fechaFinal;
        this.pesoFinal = pesoFinal;
    }
    //3_sin_ID

    public Dieta(String nombre, Paciente paciente, LocalDate fechaInicial, double pesoInicial, LocalDate fechaFinal, double pesoFinal) {
        this.nombre = nombre;
        this.paciente = paciente;
        this.fechaInicial = fechaInicial;
        this.pesoInicial = pesoInicial;
        this.fechaFinal = fechaFinal;
        this.pesoFinal = pesoFinal;
    }
    // getter and setter
    public int getIdDieta() {
        return idDieta;
    }
    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public LocalDate getFechaInicial() {
        return fechaInicial;
    }
    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    public double getPesoInicial() {
        return pesoInicial;
    }
    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }
    public LocalDate getFechaFinal() {
        return fechaFinal;
    }
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public double getPesoFinal() {
        return pesoFinal;
    }
    public void setPesoFinal(double pesoFinal) {
        this.pesoFinal = pesoFinal;
    }
    //to_string

    @Override
    public String toString() {
        return "Dieta{" + "idDieta=" + idDieta + ", nombre=" + nombre + ", idPaciente=" + paciente + ", fechaInicial=" + fechaInicial + ", pesoInicial=" + pesoInicial + ", fechaFinal=" + fechaFinal + ", pesoFinal=" + pesoFinal + '}';
    }

   
     
}

//fin del archivo
