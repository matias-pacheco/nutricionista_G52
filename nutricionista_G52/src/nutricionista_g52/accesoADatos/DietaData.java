/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.accesoADatos;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import nutricionista_g52.entidades.Dieta;
import nutricionista_g52.entidades.Historial_Peso;
import nutricionista_g52.entidades.Paciente;

/**
 *
 * @author Matías Pacheco
 */
public class DietaData {
    private Connection conex;
    private PacienteData pacData;
    
    public DietaData(){
        this.conex = Conexion.conectar();
        this.pacData = new PacienteData();
    }
    
    public void guardarDieta(Dieta dieta){
        String sql = "INSERT INTO dieta (nombre, idPaciente, fechaInicial, pesoInicial, fechaFinal, pesoFinal) "
                + "SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS("
                    + "SELECT 1 FROM dieta WHERE idPaciente = ? AND fechaFinal >= ?"
                + ");";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dieta.getNombre());
            ps.setInt(2, dieta.getPaciente().getIdPaciente());
            ps.setDate(3, Date.valueOf(dieta.getFechaInicial()));
            ps.setDouble(4, dieta.getPesoInicial());
            ps.setDate(5, Date.valueOf(dieta.getFechaFinal()));
            ps.setDouble(6, dieta.getPesoFinal());
            
            ps.setInt(7, dieta.getPaciente().getIdPaciente());
            ps.setDate(8, Date.valueOf(dieta.getFechaInicial()));
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                dieta.setIdDieta(rs.getInt(1));
                
                JOptionPane.showMessageDialog(null, "Dieta guardada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede iniciar otra dieta hasta que el \npaciente no "
                        + "finalize la que está en proceso", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
    }
    
    public Dieta buscarDieta(int id){
        String sql = "SELECT dieta.* FROM dieta JOIN paciente ON(dieta.idPaciente = paciente.idPaciente) WHERE "
                + "idDieta = ? AND idDieta IN("
                    + "SELECT MAX(idDieta) FROM dieta WHERE idPaciente IN("
                        + "SELECT idPaciente FROM dieta WHERE idDieta = ?"
                    + ")"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dieta dieta = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("nombre"));
                dieta.setPaciente(pacData.buscarPaciente(rs.getInt("idPaciente")));
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la dieta del paciente", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
     
        return dieta;
    }
    
    public Dieta buscarDietaPorIdPaciente(int id){
        String sql = "SELECT dieta.* FROM dieta JOIN paciente ON(dieta.idPaciente = paciente.idPaciente) WHERE "
                + "dieta.idPaciente = ? AND fechaInicial IN("
                    + "SELECT MAX(fechaInicial) FROM dieta WHERE idPaciente = ?"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dieta dieta = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("nombre"));
                dieta.setPaciente(pacData.buscarPaciente(rs.getInt("idPaciente")));
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la dieta del paciente", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return dieta;
    }
    
    public Dieta buscarDietaPorDni(int dni){
        String sql = "SELECT dieta.* FROM dieta JOIN paciente ON(dieta.idPaciente = paciente.idPaciente) "
                + "WHERE dni = ? AND fechaInicial IN("
                    + "SELECT MAX(fechaInicial) FROM dieta JOIN paciente ON(dieta.idPaciente = "
                    + "paciente.idPaciente) WHERE dni = ?"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dieta dieta = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dni);
            ps.setInt(2, dni);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("nombre"));
                dieta.setPaciente(pacData.buscarPaciente(rs.getInt("idPaciente")));
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la dieta del paciente", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return dieta;
    }
    
    public void modificarDieta(Dieta dieta){
        String sql = "UPDATE dieta SET nombre = ?, idPaciente = ?, fechaInicial = ?, pesoInicial = ?, "
                + "fechaFinal = ?, pesoFinal = ? WHERE idDieta = ?;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, dieta.getNombre());
            ps.setInt(2, dieta.getPaciente().getIdPaciente());
            ps.setDate(3, Date.valueOf(dieta.getFechaInicial()));
            ps.setDouble(4, dieta.getPesoInicial());
            ps.setDate(5, Date.valueOf(dieta.getFechaFinal()));
            ps.setDouble(6, dieta.getPesoFinal());
            ps.setInt(7, dieta.getIdDieta());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Dieta modificada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar la dieta del paciente", "  Mensaje", 
                        1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódio de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
        }
    }
    
    public List<Object> listarPacientesDietaTerminada(){
        String sql = "SELECT paciente.*, dieta.* FROM paciente JOIN dieta ON(paciente.idPaciente = dieta.idPaciente) "
                + "WHERE dieta.idPaciente NOT IN("
                    + "SELECT idPaciente FROM dieta WHERE fechaFinal > CURRENT_DATE"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("paciente.idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("paciente.nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                Dieta dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("dieta.nombre"));
                dieta.setPaciente(pacData.buscarPaciente(rs.getInt("dieta.idPaciente")));
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
                
                registro.add(paciente);
                registro.add(dieta);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return registro;
    }
    
    public List<Object> listarPacientesDietaTerminadaB(){
        String sql = "SELECT dieta.* FROM paciente JOIN dieta ON(paciente.idPaciente = dieta.idPaciente) "
                + "WHERE dieta.idPaciente NOT IN("
                    + "SELECT idPaciente FROM dieta WHERE fechaFinal > CURRENT_DATE"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = pacData.buscarPaciente(rs.getInt("dieta.idPaciente"));
                
                Dieta dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("dieta.nombre"));
                dieta.setPaciente(paciente);
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
                
                registro.add(paciente);
                registro.add(dieta);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return registro;
    }
    
    public List<Object> listarPacientesDietaVigente(){
        String sql = "SELECT dieta.* FROM dieta JOIN paciente ON(dieta.idPaciente = paciente.idPaciente) "
                + "WHERE fechaFinal > CURRENT_DATE AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = pacData.buscarPaciente(rs.getInt("idPaciente"));
                
                Dieta dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("nombre"));
                dieta.setPaciente(paciente);
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
                
                registro.add(paciente);
                registro.add(dieta);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return registro;
    }
    
    public List<Object> listarPacientesQueNoAlcanzaronPesoBuscado(){
        String sql = "SELECT paciente.*, dieta.*, historial_peso.* FROM paciente JOIN dieta ON("
                + "paciente.idPaciente = dieta.idPaciente) JOIN historial_peso ON(paciente.idPaciente = "
                + "historial_peso.idPaciente) WHERE dieta.idpaciente NOT IN("
                    + "SELECT idPaciente FROM dieta WHERE fechaFinal > CURRENT_DATE"
                + ") AND fecha IN("
                    + "SELECT MAX(fecha) FROM historial_peso GROUP BY idPaciente"
                + ") AND peso < pesoFinal AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("paciente.idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("paciente.nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                Dieta dieta = new Dieta();
                dieta.setIdDieta(rs.getInt("idDieta"));
                dieta.setNombre(rs.getString("dieta.nombre"));
                dieta.setPaciente(pacData.buscarPaciente(rs.getInt("dieta.idPaciente")));
                dieta.setFechaInicial(rs.getDate("fechaInicial").toLocalDate());
                dieta.setPesoInicial(rs.getDouble("pesoInicial"));
                dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
                dieta.setPesoFinal(rs.getDouble("pesoFinal"));
                
                Historial_Peso historialPeso = new Historial_Peso();
                historialPeso.setIdHistorialPeso(rs.getInt("idHistorialPeso"));
                historialPeso.setPaciente(pacData.buscarPaciente(rs.getInt("historial_peso.idPaciente")));
                historialPeso.setFecha(rs.getDate("fecha").toLocalDate());
                historialPeso.setPeso(rs.getDouble("peso"));
                
                registro.add(paciente);
                registro.add(dieta);
                registro.add(historialPeso);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return registro;
    }
    
    private void cerrarPreparedStatement(PreparedStatement ps){
        if(ps != null){
            try{
                ps.close();
            } catch(SQLException sqle){
                System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
            }
        }
    }
    
    private void cerrarResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch(SQLException sqle){
                System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
            }
        }
    }
}