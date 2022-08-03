/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

/**
 *
 * @author rob99
 */
import java.sql.Connection;
import java.sql.DriverManager;
public class Conect {
    
    
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    public static final String USER = "root";
    public static final String CLAVE = "L4v4$25EDIN8";

    public Conect() {
        
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
            System.out.println("Conexion realizada");
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
    }
    
    
     
    
    }
}
