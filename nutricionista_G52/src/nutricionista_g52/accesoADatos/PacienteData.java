/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.accesoADatos;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import nutricionista_g52.entidades.Paciente;

/**
 *
 * @author Matías Pacheco
 */
public class PacienteData {
    private Connection conex;
    
    public PacienteData(){
        this.conex = Conexion.conectar();
    }
    
    public void guardarPaciente(Paciente paciente){
        String sql = "INSERT INTO paciente (dni, apellido, nombre, domicilio, telefono, estado) "
                + "VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, paciente.getDni());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getDomicilio());
            ps.setString(5, paciente.getTelefono());
            ps.setBoolean(6, paciente.getEstado());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                paciente.setIdPaciente(rs.getInt(1));
                
                JOptionPane.showMessageDialog(null, "Paciente guardado", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar paciente", "  Mensaje", 1);
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
    
    public Paciente buscarPaciente(int id){
        String sql = "SELECT * FROM paciente WHERE idPaciente = ? AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paciente paciente = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el paciente", "  Mensaje", 1);
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
        
        return paciente;
    }
    
    public Paciente buscarpacientePorDni(int dni){
        String sql = "SELECT * FROM paciente WHERE dni = ? AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Paciente paciente = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dni);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el paciente", "  Mensaje", 1);
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
        
        return paciente;
    }
    
    public void modificarPaciente(Paciente paciente){
        String sql = "UPDATE paciente SET dni = ?, apellido = ?, nombre = ?, domicilio = ?, telefono = ? "
                + "WHERE idPaciente = ? AND estado = 1;"; //Revisar si se va a requerir o no, en la condición, 
                                           //el estado del paciente
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, paciente.getDni());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getDomicilio());
            ps.setString(5, paciente.getTelefono());
            ps.setInt(6, paciente.getIdPaciente());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Paciente modificado", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro paciente", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
        }
    }
    
    public void borrarPaciente(int id){
        String sql = "UPDATE paciente SET estado = 0 WHERE idPaciente = ? AND estado = 1;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
            int borrados = ps.executeUpdate();
            
            if(borrados == 1){
                JOptionPane.showMessageDialog(null, "Paciente eliminado", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro paciente");
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
        }
    }
    
    public void restaurarPaciente(int id){
        String sql = "UPDATE paciente SET estado = 1 WHERE idPaciente = ? AND estado = 0;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
            int restaurados = ps.executeUpdate();
            
            if(restaurados == 1){
                JOptionPane.showMessageDialog(null, "Paciente restaurado", "  Mensaje", 1);
            } else{
                JOptionPane.showMessageDialog(null, "No se encontro paciente", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
        }
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
                System.err.println(sqle.getMessage()+"Código de ERROR: "+sqle.getErrorCode());
            }
        }
    }
}