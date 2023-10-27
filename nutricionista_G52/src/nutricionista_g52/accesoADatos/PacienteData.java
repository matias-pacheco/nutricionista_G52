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
import java.util.ArrayList;
import java.util.List;
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
    
//    public void guardarPaciente(Paciente paciente){
//        String sql = "INSERT INTO paciente (dni, apellido, nombre, domicilio, telefono, estado) "
//                + "VALUES(?, ?, ?, ?, ?, ?);";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        
//        try{
//            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, paciente.getDni());
//            ps.setString(2, paciente.getApellido());
//            ps.setString(3, paciente.getNombre());
//            ps.setString(4, paciente.getDomicilio());
//            ps.setString(5, paciente.getTelefono());
//            ps.setBoolean(6, paciente.getEstado());
//            ps.executeUpdate();
//            
//            rs = ps.getGeneratedKeys();
//            
//            if(rs.next()){
//                paciente.setIdPaciente(rs.getInt(1));
//                
//                JOptionPane.showMessageDialog(null, "Paciente guardado", "  Mensaje", 1);
//            } else {
//                JOptionPane.showMessageDialog(null, "No se pudo guardar paciente", "  Mensaje", 1);
//            }
//        } catch(SQLException sqle){
//            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
//        } catch(HeadlessException he){
//            System.err.println(he.getMessage());
//        } catch(Exception e){
//            e.printStackTrace();
//        } finally {
//            cerrarPreparedStatement(ps);
//            cerrarResultSet(rs);
//        }
//    }
    public boolean guardarPaciente(Paciente paciente){
        String sql = "INSERT INTO paciente (dni, apellido, nombre, domicilio, telefono, estado) "
                + "SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS("
                    + "SELECT dni, telefono FROM paciente WHERE dni = ? OR REPLACE(telefono, ' ', '') IN("
                        + "SELECT REPLACE(?, ' ', '') FROM paciente"
                    + ")"
                + ")";
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
            
            ps.setInt(7, paciente.getDni());
            ps.setString(8, paciente.getTelefono());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                paciente.setIdPaciente(rs.getInt(1));
                
                JOptionPane.showMessageDialog(null, "Paciente guardado", "  Mensaje", 1);
                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se guardo el paciente. Revise que el número de documento"
                        + "\no de teléfono sea el correcto y vuelva a intentar", "  Mensaje", 1);
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
        
        return false;
    }
    
    public Paciente buscarPacientePorId(int id){
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
    
    public List<Paciente> listarPacientesPorApellido(String apellido){
        String sql = "SELECT * FROM paciente WHERE UPPER(apellido) LIKE UPPER(?) AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, apellido);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                registro.add(paciente);
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
        
        return registro;
    }
    
    public List<Paciente> listarPacientesPorDni(String dni){
        String sql = "SELECT * FROM paciente WHERE CONVERT(dni, CHAR) LIKE ? AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, dni);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                registro.add(paciente);
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
        
        return registro;
    }
    
    public List<Paciente> listarPacientesBorradosPorApellido(String apellido){
        String sql = "SELECT * FROM paciente WHERE UPPER(apellido) LIKE UPPER(?) AND estado = 0;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, apellido);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                registro.add(paciente);
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
        
        return registro;
    }
    
    public List<Paciente> listarPacientesBorradosPorDni(String dni){
        String sql = "SELECT * FROM paciente WHERE CONVERT(dni, CHAR) LIKE ? AND estado = 0;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, dni);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                registro.add(paciente);
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
        
        return registro;
    }
    
    public boolean buscarPacientePorDni(int dni){
        String sql = "SELECT 1 FROM paciente WHERE dni = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dni);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return false;
    }
    
    public boolean buscarPacientePorTelefono(String telefono){
        String sql = "SELECT 1 FROM paciente WHERE REPLACE(telefono, ' ', '') IN("
                    + "SELECT REPLACE(?, ' ', '') FROM paciente"
                + ")";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, telefono);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return false;
    }
    
    
    
//    public void modificarPaciente(Paciente paciente){
//        String sql = "UPDATE paciente SET dni = ?, apellido = ?, nombre = ?, domicilio = ?, telefono = ? "
//                + "WHERE idPaciente = ? AND estado = 1;"; //Revisar si se va a requerir o no, en la condición, 
//                                           //el estado del paciente
//        PreparedStatement ps = null;
//        
//        try{
//            ps = conex.prepareStatement(sql);
//            ps.setInt(1, paciente.getDni());
//            ps.setString(2, paciente.getApellido());
//            ps.setString(3, paciente.getNombre());
//            ps.setString(4, paciente.getDomicilio());
//            ps.setString(5, paciente.getTelefono());
//            ps.setInt(6, paciente.getIdPaciente());
//            
//            int modificados = ps.executeUpdate();
//            
//            if(modificados == 1){
//                JOptionPane.showMessageDialog(null, "Paciente modificado", "  Mensaje", 1);
//            } else {
//                JOptionPane.showMessageDialog(null, "No se encontro paciente", "  Mensaje", 1);
//            }
//        } catch(SQLException sqle){
//            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
//        } catch(HeadlessException he){
//            System.err.println(he.getMessage());
//        } catch(Exception e){
//            e.printStackTrace();
//        } finally {
//            cerrarPreparedStatement(ps);
//        }
//    }
    
    public boolean modificarPaciente(Paciente paciente){
        String sql = "UPDATE paciente SET dni = ?, apellido = ?, nombre = ?, domicilio = ?, telefono = ? "
                + "WHERE idPaciente = ? AND NOT EXISTS("
                    + "SELECT 1 FROM paciente WHERE (idPaciente != ? AND (dni = ? OR REPLACE(telefono, ' ', '') "
                    + "LIKE REPLACE(?, ' ', ''))) OR (idPaciente = ? AND dni = ? AND BINARY apellido LIKE ? AND BINARY nombre "
                    + "LIKE ? AND BINARY domicilio LIKE ? AND REPLACE(telefono, ' ', '') LIKE REPLACE(?, ' ', ''))"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, paciente.getDni());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getDomicilio());
            ps.setString(5, paciente.getTelefono());
            ps.setInt(6, paciente.getIdPaciente());
            
            ps.setInt(7, paciente.getIdPaciente());
            ps.setInt(8, paciente.getDni());
            ps.setString(9, paciente.getTelefono());
            
            ps.setInt(10, paciente.getIdPaciente());
            ps.setInt(11, paciente.getDni());
            ps.setString(12, paciente.getApellido());
            ps.setString(13, paciente.getNombre());
            ps.setString(14, paciente.getDomicilio());
            ps.setString(15, paciente.getTelefono());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Paciente modificado", "  Mensaje", 1);
                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se guardo ninguna modificación. Debe realizar al menos una modificación para "
                        + "\nguardarla. Si ha realizado modificaciones en el número de documento o de teléfono"
                        + "\nverifique que el nuevo número sea el correcto", "  Mensaje", 1);
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
        
        return false;
    }
    
        public boolean modificarPacienteMayorRestriccion(Paciente paciente){
        String sql = "UPDATE paciente SET dni = ?, apellido = ?, nombre = ?, domicilio = ?, telefono = ? "
                + "WHERE idPaciente = ? AND NOT EXISTS("
                    + "SELECT 1 FROM paciente WHERE (idPaciente != ? AND (dni = ? OR REPLACE(telefono, ' ', '') "
                    + "LIKE REPLACE(?, ' ', ''))) OR (idPaciente = ? AND dni = ? AND UPPER(apellido) LIKE UPPER(?) AND UPPER(nombre) "
                    + "LIKE UPPER(?) AND UPPER(domicilio) LIKE UPPER(?) AND REPLACE(telefono, ' ', '') LIKE REPLACE(?, ' ', ''))"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, paciente.getDni());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getDomicilio());
            ps.setString(5, paciente.getTelefono());
            ps.setInt(6, paciente.getIdPaciente());
            
            ps.setInt(7, paciente.getIdPaciente());
            ps.setInt(8, paciente.getDni());
            ps.setString(9, paciente.getTelefono());
            
            ps.setInt(10, paciente.getIdPaciente());
            ps.setInt(11, paciente.getDni());
            ps.setString(12, paciente.getApellido());
            ps.setString(13, paciente.getNombre());
            ps.setString(14, paciente.getDomicilio());
            ps.setString(15, paciente.getTelefono());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Paciente modificado", "  Mensaje", 1);
                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se guardo ninguna modificación. Debe realizar al menos una modificación para guardarla."
                        + "\nSi ha realizado modificaciones en el número de documento o de teléfono verifique"
                        + "\nque el nuevo número sea el correcto", "  Mensaje", 1);
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
        
        return false;
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
    
    public void borrarPacientePorDni(int dni){
        String sql = "UPDATE paciente SET estado = 0 WHERE dni = ? AND estado = 1;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dni);
            
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
    
    public void restaurarPacientePorDni(int dni){
        String sql = "UPDATE paciente SET estado = 1 WHERE dni = ? AND estado = 0;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dni);
            
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
    
    public List<Paciente> listarPacientes(){
        String sql = "SELECT * FROM paciente WHERE estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                registro.add(paciente);
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
    
    public List<Paciente> listarPacientesBorrados(){
        String sql = "SELECT * FROM paciente WHERE estado = 0;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDomicilio(rs.getString("domicilio"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setEstado(rs.getBoolean("estado"));
                
                registro.add(paciente);
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
                System.err.println(sqle.getMessage()+"Código de ERROR: "+sqle.getErrorCode());
            }
        }
    }
}