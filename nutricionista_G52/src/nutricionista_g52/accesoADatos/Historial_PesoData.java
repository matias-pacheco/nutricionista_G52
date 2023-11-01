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
import nutricionista_g52.entidades.Historial_Peso;

/**
 *
 * @author Matías Pacheco
 */
public class Historial_PesoData {
    private Connection conex;
    private PacienteData pacData;
    
    public Historial_PesoData(){
        this.conex = Conexion.conectar();
        this.pacData = new PacienteData();
    }
    
//    public void guardarHistorial_Peso(Historial_Peso historialPeso){
//        String sql = "INSERT INTO historial_peso (idPaciente, fecha, peso) VALUES(?, ?, ?);";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        
//        try{
//            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, historialPeso.getPaciente().getIdPaciente());
//            ps.setDate(2, Date.valueOf(historialPeso.getFecha()));
//            ps.setDouble(3, historialPeso.getPeso());
//            ps.executeUpdate();
//            
//            rs = ps.getGeneratedKeys();
//            
//            if(rs.next()){
//                historialPeso.setIdHistorialPeso(rs.getInt(1));
//                
//                JOptionPane.showMessageDialog(null, "Peso guardado", "  Mensaje", 1);
//            } else {
//                JOptionPane.showMessageDialog(null, "No se pudo guardar peso", "  Mensaje", 1);
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
    
    public void guardarHistorial_Peso(Historial_Peso historialPeso){
        String sql = "INSERT INTO historial_peso (idPaciente, fecha, peso) "
                + "SELECT ?, ?, ? WHERE NOT EXISTS("
                    + "SELECT 1 FROM dieta WHERE idPaciente = ? AND ((SELECT MAX(fechaFinal) FROM dieta WHERE idPaciente = ?) "
                    + "< CURRENT_DATE OR (SELECT MAX(fecha) FROM historial_peso WHERE idPaciente = ?) >= ?)" //Podría ir Current_Date pero para llenar la tabla con otras fecha lo puse así
                + ");";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, historialPeso.getPaciente().getIdPaciente());
            ps.setDate(2, Date.valueOf(historialPeso.getFecha()));
            ps.setDouble(3, historialPeso.getPeso());
            
            ps.setInt(4, historialPeso.getPaciente().getIdPaciente());
            ps.setInt(5, historialPeso.getPaciente().getIdPaciente());
            ps.setInt(6, historialPeso.getPaciente().getIdPaciente());
            ps.setDate(7, Date.valueOf(historialPeso.getFecha()));
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                historialPeso.setIdHistorialPeso(rs.getInt(1));
                
                JOptionPane.showMessageDialog(null, "Peso guardado", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se guardo el peso. El paciente termino su dieta o ya "
                        + "\nse ha ingresado su peso el día vigente", "  Mensaje", 1);
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
    
    public Historial_Peso buscarPesoActual(int idpaciente){
        String sql = "SELECT historial_peso.* FROM historial_peso JOIN paciente ON(historial_peso.idPaciente = paciente.IdPaciente) "
                + "WHERE paciente.idPaciente = ? AND fecha IN("
                    + "SELECT MAX(fecha) FROM historial_peso WHERE idPaciente = ?"
                + ") AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Historial_Peso hisPeso = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            ps.setInt(2, idpaciente);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                hisPeso = new Historial_Peso();
                hisPeso.setIdHistorialPeso(rs.getInt("idHistorialPeso"));
                hisPeso.setPaciente(pacData.buscarPacientePorId(rs.getInt("idPaciente")));
                hisPeso.setFecha(rs.getDate("fecha").toLocalDate());
                hisPeso.setPeso(rs.getDouble("peso"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el peso del paciente", "  Mensaje", 1);
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
        
        return hisPeso;
    }
    
    public void modificarHistorial_Peso(Historial_Peso historialPeso){
        String sql = "UPDATE historial_peso SET idPaciente = ?, fecha = ?, peso = ? WHERE idHistorialPeso = ?;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, historialPeso.getPaciente().getIdPaciente());
            ps.setDate(2, Date.valueOf(historialPeso.getFecha()));
            ps.setDouble(3, historialPeso.getPeso());
            ps.setInt(4, historialPeso.getIdHistorialPeso());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Peso modificado", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar el peso del paciente", "  Mensaje", 1);
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
    
    public List<Historial_Peso> listarHistorial_Peso(int idPaciente){
        String sql = "SELECT historial_peso.* FROM historial_peso JOIN paciente ON(historial_peso.idPaciente = paciente.idPaciente) "
                + "WHERE paciente.idPaciente = ? AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Historial_Peso> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Historial_Peso historialPeso = new Historial_Peso();
                historialPeso.setIdHistorialPeso(rs.getInt("idHistorialPeso"));
                historialPeso.setPaciente(pacData.buscarPacientePorId(rs.getInt("idPaciente")));
                historialPeso.setFecha(rs.getDate("fecha").toLocalDate());
                historialPeso.setPeso(rs.getDouble("peso"));
                
                registro.add(historialPeso);
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
    
    public List<Historial_Peso> listarHistorial_PesoPorOrdenAscODesc(int idPaciente, String orden){
        String sql = "SELECT historial_peso.* FROM historial_peso JOIN paciente ON(historial_peso.idPaciente = paciente.idPaciente) "
                + "WHERE paciente.idPaciente = ? AND estado = 1 ORDER BY fecha "+orden+";";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Historial_Peso> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Historial_Peso historialPeso = new Historial_Peso();
                historialPeso.setIdHistorialPeso(rs.getInt("idHistorialPeso"));
                historialPeso.setPaciente(pacData.buscarPacientePorId(rs.getInt("idPaciente")));
                historialPeso.setFecha(rs.getDate("fecha").toLocalDate());
                historialPeso.setPeso(rs.getDouble("peso"));
                
                registro.add(historialPeso);
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
    
    public List<Historial_Peso> listarHistorial_PesoUltimaDietaPorOrdenAscODesc(int idPaciente, String orden){
        String sql = "SELECT historial_peso.* FROM historial_peso JOIN paciente ON(historial_peso.idPaciente = paciente.idPaciente) "
                + "WHERE historial_peso.idPaciente = ? AND estado = 1 AND fecha >= (SELECT MAX(fechaInicial) FROM dieta WHERE "
                + "idPaciente = ?) AND fecha <= (SELECT MAX(fechaFinal) FROM dieta WHERE idPaciente = ?) ORDER BY fecha "+orden+";";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Historial_Peso> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ps.setInt(2, idPaciente);
            ps.setInt(3, idPaciente);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Historial_Peso historialPeso = new Historial_Peso();
                historialPeso.setIdHistorialPeso(rs.getInt("idHistorialPeso"));
                historialPeso.setPaciente(pacData.buscarPacientePorId(rs.getInt("idPaciente")));
                historialPeso.setFecha(rs.getDate("fecha").toLocalDate());
                historialPeso.setPeso(rs.getDouble("peso"));
                
                registro.add(historialPeso);
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
