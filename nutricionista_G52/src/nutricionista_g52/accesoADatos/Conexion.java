/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matías Pacheco
 */
public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "nutricionista_prueba";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    
    private Conexion(){}
    
    public static Connection conectar(){
        if(connection == null){
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                
                connection = DriverManager.getConnection(URL+DB+"?useLegacyDatetimeCode=false&serverTimezone=UTC"
                        + "&user="+USER+"&password="+PASSWORD);
            } catch(ClassNotFoundException cnfe){
                System.err.println(cnfe.getMessage());
            } catch(SQLException sqle){
                System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return connection;
    }
    
    public static Connection desconectar(){
        if(connection != null){
            try{
                connection.close();
            } catch(SQLException sqle){
                System.err.println(sqle.getMessage()+"\nCódigo de ERROR: "+sqle.getErrorCode());
            }
        }
        
        return connection;
    }
}
