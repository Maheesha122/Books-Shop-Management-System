/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Keshani Perera
 */
public class databaseConnection {
    
    
        public static Connection connect(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/citybookshop","root","");
        }
        catch (Exception e){
        }
    return conn;
    }
    
    
}
