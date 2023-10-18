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
import nutricionista_g52.entidades.Dieta_Comida;
import nutricionista_g52.entidades.enumeraciones.HorarioAlimenticio;

/**
 *
 * @author Matías Pacheco
 */
public class Dieta_ComidaData {
    private Connection conex;
    private ComidaData comiData;
    private DietaData dieData;
    
    public Dieta_ComidaData(){
        this.conex = Conexion.conectar();
        this.comiData = new ComidaData();
        this.dieData = new DietaData();
    }
    
    public void guardarDieta_Comida(Dieta_Comida dietaComida){
        String sql = "INSERT INTO dieta_comida (idComida, idDieta, horario, porcion) "
                + "SELECT ?, ?, ?, ? WHERE NOT EXISTS("
                    + "SELECT 1 FROM dieta_comida WHERE idComida = ? AND idDieta = ? AND horario LIKE ?"
                + ");";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dietaComida.getComida().getIdComida());
            ps.setInt(2, dietaComida.getDieta().getIdDieta());
            ps.setString(3, dietaComida.getHorario().toString());
            ps.setInt(4, dietaComida.getPorcion());
            
            ps.setInt(5, dietaComida.getComida().getIdComida());
            ps.setInt(6, dietaComida.getDieta().getIdDieta());
            ps.setString(7, dietaComida.getHorario().toString());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                dietaComida.setIdDietaComida(rs.getInt(1));
                
                JOptionPane.showMessageDialog(null, "Se agrego comida a la dieta", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar comida a la dieta", "  Mensaje", 1);
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
    
    public Dieta_Comida buscarDieta_Comida(String nombreComida, int dni, HorarioAlimenticio horario){
        String sql = "SELECT dieta_comida.* FROM dieta_comida JOIN comida ON(dieta_comida.idComida = "
                + "comida.idComida) JOIN dieta ON(dieta_comida.idDieta = dieta.idDieta) WHERE "
                + "comida.nombre LIKE ? AND estado = 1 AND dieta_comida.idDieta = ? AND horario LIKE ?;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dieta_Comida dietaComida = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, nombreComida);
            ps.setInt(2, dieData.buscarDietaPorDni(dni).getIdDieta());
            ps.setString(3, horario.toString());
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                dietaComida = new Dieta_Comida();
                dietaComida.setIdDietaComida(rs.getInt("idDietaComida"));
                dietaComida.setComida(comiData.buscarComidaPorId(rs.getInt("idComida")));
                dietaComida.setDieta(dieData.buscarDieta(rs.getInt("idDieta")));
                dietaComida.setHorario(HorarioAlimenticio.valueOf(rs.getString("horario")));
                dietaComida.setPorcion(rs.getInt("porcion"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la comida, dieta del paciente u horario", 
                        "  Mensaje", 1);
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
        
        return dietaComida;
    }
    
    public Dieta_Comida buscarDieta_ComidaB(String nombreComida, int dni, HorarioAlimenticio horario){
        String sql = "SELECT dieta_comida.* FROM dieta_comida JOIN comida ON(dieta_comida.idComida = "
                + "comida.idComida) JOIN dieta ON(dieta_comida.idDieta = dieta.idDieta) WHERE "
                + "comida.nombre LIKE ? AND estado = 1 AND dieta_comida.idDieta IN("
                    + "SELECT idDieta FROM dieta JOIN paciente ON(dieta.idPaciente = "
                    + "paciente.idPaciente) WHERE dni = ? AND estado = 1 AND fechaInicial IN("
                        + "SELECT MAX(fechaInicial) FROM dieta JOIN paciente ON(dieta.idPaciente = "
                        + "paciente.idPaciente) WHERE dni = ?"
                    +")"
                + ") AND horario = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dieta_Comida dietaComida = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setString(1, nombreComida);
            ps.setInt(2, dni);
            ps.setInt(3, dni);
            ps.setString(4, horario.toString());
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                dietaComida = new Dieta_Comida();
                dietaComida.setIdDietaComida(rs.getInt("idDietaComida"));
                dietaComida.setComida(comiData.buscarComidaPorId(rs.getInt("idComida")));
                dietaComida.setDieta(dieData.buscarDieta(rs.getInt("idDieta")));
                dietaComida.setHorario(HorarioAlimenticio.valueOf(rs.getString("horario")));
                dietaComida.setPorcion(rs.getInt("porcion"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro la comida, dieta del paciente u horario", 
                        "  Mensaje", 1);
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
        
        return dietaComida;
    }
    
    public void modificarDieta_Comida(Dieta_Comida dietaComida){
        String sql = "UPDATE dieta_comida SET idComida = ?, horario = ?, porcion = ? WHERE idDieta IN("
                        + "SELECT idDieta FROM dieta WHERE fechaInicial IN("
                            + "SELECT MAX(fechaInicial) FROM dieta WHERE idPaciente IN("
                                + "SELECT dieta.idPaciente FROM dieta JOIN paciente ON(dieta.idPaciente = "
                                + "paciente.idPaciente) WHERE idDieta = ? AND estado = 1"
                            + ")"
                        + ")"
                    + ") AND idComida IN("
                        + "SELECT idComida FROM comida WHERE idComida = ? AND estado = 1"
                    + ") AND NOT EXISTS("
                        + "SELECT 1 FROM dieta_comida WHERE idComida = ? AND idDieta = ? AND horario LIKE ? "
                        + "AND porcion = ?"
                    + ")";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dietaComida.getComida().getIdComida());
            ps.setString(2, dietaComida.getHorario().toString());
            ps.setInt(3, dietaComida.getPorcion());
            
            ps.setInt(4, dietaComida.getDieta().getIdDieta());
            ps.setInt(5, dietaComida.getComida().getIdComida());
            ps.setInt(6, dietaComida.getComida().getIdComida());
            ps.setInt(7, dietaComida.getDieta().getIdDieta());
            ps.setString(8, dietaComida.getHorario().toString());
            ps.setInt(9, dietaComida.getPorcion());
            
            int modificados = ps.executeUpdate();
            
            if(modificados == 1){
                JOptionPane.showMessageDialog(null, "Comida, horario o porción modificada", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar la comida, horario o porción", 
                        "  Mensaje", 1);
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
    
    public void borrarComidaDeDieta(Dieta_Comida dietaComida){
        String sql = "DELETE FROM dieta_comida WHERE idComida IN("
                    + "SELECT idComida FROM comida WHERE idComida = ? AND estado = 1"
                + ") AND idDieta IN("
                    + "SELECT idDieta FROM dieta WHERE fechaInicial IN("
                        + "SELECT MAX(fechaInicial) FROM dieta WHERE idPaciente IN("
                            + "SELECT dieta.idPaciente FROM dieta JOIN paciente ON(dieta.idPaciente = "
                            + "paciente.idPaciente) WHERE idDieta = ? AND estado = 1"
                        + ")"
                    + ")"
                + ") AND horario LIKE ?";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, dietaComida.getComida().getIdComida());
            ps.setInt(2, dietaComida.getDieta().getIdDieta());
            ps.setString(3, dietaComida.getHorario().toString());
            
            int eliminados = ps.executeUpdate();
            
            if(eliminados == 1){
                JOptionPane.showMessageDialog(null, "Se elimino la comida de la dieta", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la comida de la dieta", "  Mensaje", 1);
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
    
    public void borrarComida_Dieta(int id){
        String sql = "DELETE FROM dieta_comida WHERE idComida IN("
                    + "SELECT dieta_comida.idComida FROM dieta_comida JOIN comida ON(dieta_comida.idComida = "
                    + "comida.idComida) WHERE idDietaComida = ? AND estado = 1"
                + ") AND idDieta IN("
                    + "SELECT idDieta FROM dieta WHERE fechaInicial IN("
                        + "SELECT MAX(fechaInicial) FROM dieta WHERE idPaciente IN("
                            + "SELECT dieta.idPaciente FROM dieta JOIN paciente ON(dieta.idPaciente = "
                            + "paciente.idPaciente) JOIN dieta_comida ON(dieta.idDieta = dieta_comida.idDieta) "
                            + "WHERE dieta_comida.idDieta IN("
                                + "SELECT idDieta FROM dieta_comida WHERE idDietaComida = ?"
                            + ") AND estado = 1"
                        + ")"
                    + ")"
                + ") AND horario IN("
                    + "SELECT horario FROM dieta_comida WHERE idDietaComida = ?"
                + ");";
        PreparedStatement ps = null;
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            
            int eliminados = ps.executeUpdate();
            
            if(eliminados == 1){
                JOptionPane.showMessageDialog(null, "Se elimino la comida de la dieta", "  Mensaje", 1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la comida de la dieta", "  Mensaje", 1);
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
    
    public List<Comida> listarComidasDeDietaPorIdPaciente(int id){
        String sql = "SELECT comida.* FROM comida JOIN dieta_comida ON(comida.idComida = dieta_comida.idComida) "
                + "WHERE idDietaComida IN("
                    + "SELECT idDietaComida FROM dieta_comida WHERE idDieta IN("
                        + "SELECT idDieta FROM dieta WHERE fechaInicial IN("
                            + "SELECT MAX(fechaInicial) FROM dieta JOIN paciente ON(dieta.idPaciente = "
                            + "paciente.idPaciente) WHERE dieta.idPaciente = ? AND estado = 1"
                        + ")"
                    + ")"
                + ") AND estado = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comida> registro = new ArrayList<>();
        
        try{
            ps = conex.prepareStatement(sql);
            ps.setInt(1, id);
            
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
