/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functions;

import databaseConnection.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import models.users;

/**
 *
 * @author Keshani Perera
 */

public class manager extends serviceFunctions {
       
Connection con = null;
PreparedStatement pst = null;
ResultSet rs = null; // Declare ResultSet
public manager() {
        con = databaseConnection.connect();
    }


    @Override
    public boolean addAccount(String txtusername, String txtpassword, String txtrole) {
        boolean success = false;
        users user = new users();
        user.setUsername(txtusername);
        user.setPassword(txtpassword);
        user.setRole(txtrole);
                
        
      try{
            
            String q = "INSERT INTO users (user_type, username, password) VALUES ('" + user.getRole() + "','" + user.getUsername() + "','" + user.getPassword() +  "')";
            pst =con.prepareStatement(q);
            pst.execute();
            success = true;
            
        }
        catch (Exception e){

        }  
    return success; 
    }

   

   
    
}
