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
import nutricionista_g52.entidades.Comida;

/**
 *
 * @author Matías Pacheco
 */
public class ComidaData {
    private Connection conex;
    
    public ComidaData(){
        this.conex = Conexion.conectar();
    }
    
    public void guardarComida(Comida comida){
        String sql = "INSERT INTO comida (nombre, detalle, cantCalorias, estado) "
                + "SELECT ?, ?, ?, ? WHERE NOT EXISTS("
                    + "SELECT nombre FROM comida WHERE nombre LIKE ?"
                + ")";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comida.getNombre());
            ps.setString(2, comida.getDetalle());
            ps.setInt(3, comida.getCantCalorias());
            ps.setBoolean(4, comida.isEstado());
            
            ps.setString(5, comida.getNombre());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                comida.setIdComida(rs.getInt(1));
                
                JOptionPane.showMessageDialog(null, "Comida guardada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar comida, verifique que el "
                        + "\nnombre ingresado no este en uso", "  Mensaje", 1);
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
    
    public Comida buscarComidaPorId(int id){
        String sql = "SELECT * FROM comida WHERE idComida = ? AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comida comida = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                comida = new Comida();
                comida.setIdComida(rs.getInt("idComida"));
                comida.setNombre(rs.getString("nombre"));
                comida.setDetalle(rs.getString("detalle"));
                comida.setCantCalorias(rs.getInt("cantCalorias"));
                comida.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la comida", "  Mensaje", 1);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódifo de ERROR: "+sqle.getErrorCode());
        } catch(HeadlessException he){
            System.err.println(he.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(ps);
            cerrarResultSet(rs);
        }
        
        return comida;
    }
    
    public Comida buscarComidaPorNombre(String nombre){
        String sql = "SELECT * FROM comida WHERE nombre LIKE ? AND estado = 1;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comida comida = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, nombre);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                comida = new Comida();
                comida.setIdComida(rs.getInt("idComida"));
                comida.setNombre(rs.getString("nombre"));
                comida.setDetalle(rs.getString("detalle"));
                comida.setCantCalorias(rs.getInt("cantCalorias"));
                comida.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la comida", "  Mensaje", 1);
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
        
        return comida;
    }
    
    public void modificarComida(Comida comida){
        String sql = "UPDATE comida SET nombre = ?, detalle = ?, cantCalorias = ? WHERE NOT EXISTS("
                    + "SELECT nombre FROM comida WHERE nombre LIKE ?"
                + ") AND idComida = ? AND estado = 1;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, comida.getNombre());
            ps.setString(2, comida.getDetalle());
            ps.setInt(3, comida.getCantCalorias());
            
            ps.setString(4, comida.getNombre());
            
            ps.setInt(5, comida.getIdComida());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Comida modificada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar comida", "  Mensaje", 1);
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
    
    public void borrarComida(int id){
        String sql = "UPDATE comida SET estado = 0 WHERE idComida = ? AND estado = 1;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
            int eliminados = ps.executeUpdate();
            
            if(eliminados == 1){
                JOptionPane.showMessageDialog(null, "Comida eliminada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la comida", "  Mensaje", 1);
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
    
    public void restaurarComida(int id){
        String sql = "UPDATE comida SET estado = 1 WHERE idComida = ? AND estado = 0;";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
            int restaurados = ps.executeUpdate();
            
            if(restaurados == 1){
                JOptionPane.showMessageDialog(null, "Comida restaurada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo restaurar comida", "  Mensaje", 1);
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
    
    public List<Comida> listarComidasConMenorCantidadCalorias(int calorias){
        String sql = "SELECT * FROM comida WHERE cantCalorias < ? AND estado = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comida> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, calorias);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Comida comida = new Comida();
                comida.setIdComida(rs.getInt("idComida"));
                comida.setNombre(rs.getString("nombre"));
                comida.setDetalle(rs.getString("detalle"));
                comida.setCantCalorias(rs.getInt("cantCalorias"));
                comida.setEstado(rs.getBoolean("estado"));
                
                registro.add(comida);
            }
        } catch(SQLException sqle){
            System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getMessage());
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