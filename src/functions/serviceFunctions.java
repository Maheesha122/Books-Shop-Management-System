/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functions;
import databaseConnection.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.books;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keshani Perera
 */
abstract public class serviceFunctions {
    
    boolean success = false;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null; // Declare ResultSet
    public serviceFunctions() {
        con = databaseConnection.connect();
    }
    
    
     protected Connection getConnection() {
        // Dummy method to simulate getting a database connection (replace with actual implementation)
        return null;

    }
    protected boolean addUser(String username, String password, String role) {
        // Dummy method to simulate adding a user (replace with actual implementation)
        return false;
    }

   public boolean addNewBook(String txtcategory, String txtname, String txtprice,String txtauthor, String txtisbn, String txtpublisher) {
        
        books book = new books();
        book.setCategory(txtcategory);
        book.setName(txtname);
        book.setPrice(Double.parseDouble(txtprice));
        book.setAuthor(txtauthor);
        book.setIsbn(txtisbn);
        book.setPublisher(txtpublisher);

      try{
            String q = "INSERT INTO books (category, name, price,author,isbn,publisher) VALUES ('" + book.getCategory() + "','" + book.getName() + "','" + book.getPrice() +  "','" + book.getAuthor() +  "','" + book.getIsbn() +  "','" + book.getPublisher() +  "')";
            pst =con.prepareStatement(q);
            pst.execute();
            success = true;
            
        }
        catch (Exception e){

        } 
        
        return success;
    }

    public boolean searchBook(String searchText, JTable book_table) {
        
        String searchtxt=searchText;
        try{
        String sql = "SELECT book_id, category, name, price, author, isbn, publisher FROM books " +
             "WHERE book_id LIKE '%" + searchText + "%' OR " +
             "category LIKE '%" + searchText + "%' OR " +
             "name LIKE '%" + searchText + "%' OR " +
             "price LIKE '%" + searchText + "%' OR " +
             "author LIKE '%" + searchText + "%' OR " +
             "isbn LIKE '%" + searchText + "%' OR " +
             "publisher LIKE '%" + searchText + "%'";
        pst =con.prepareStatement(sql);
        rs=pst.executeQuery();
        book_table.setModel(DbUtils.resultSetToTableModel(rs));
        success = true;
       }
       catch(Exception e){
       
       }
       return success;      
            
       }
   public boolean deleteBook(String bookId){
   boolean success = false; // Initialize success status
    try {
        // Construct the SQL delete query
        String sql = "DELETE FROM books WHERE book_id = ?";
        
        // Prepare the statement
        pst = con.prepareStatement(sql);
        
        // Set the bookId parameter
        pst.setString(1, bookId);
        
        // Execute the delete query
        int rowsAffected = pst.executeUpdate();
        
        // Check if the delete operation was successful
        if (rowsAffected > 0) {
            success = true; // Set success status to true
        }
        
        // Optionally, you can handle success or display a message here
        
    } catch (SQLException e) {
        e.printStackTrace(); // Print the stack trace for debugging
        // Handle any SQL exceptions here
    } finally {
        // Close resources
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print the stack trace for debugging
            // Handle any exceptions here
        }
    }
    return success;
}

    
    public boolean updateBook(String bookId, String txtcategory, String txtname, String txtprice, String txtauthor, String txtisbn, String txtpublisher) {
    boolean success = false;
    books book = new books();
    book.setCategory(txtcategory);
    book.setName(txtname);
    book.setPrice(Double.parseDouble(txtprice));
    book.setAuthor(txtauthor);
    book.setIsbn(txtisbn);
    book.setPublisher(txtpublisher);
    try {
        // Convert bookId to integer
        int id = Integer.parseInt(bookId);

        // Construct the SQL update query
        String q = "UPDATE books SET category=?, name=?, price=?, author=?, isbn=?, publisher=? WHERE book_id=?";
        pst = con.prepareStatement(q);
        pst.setString(1, book.getCategory());
        pst.setString(2, book.getName());
        pst.setDouble(3, book.getPrice());
        pst.setString(4, book.getAuthor());
        pst.setString(5, book.getIsbn());
        pst.setString(6, book.getPublisher());
        pst.setInt(7, id);
        // Execute the update query
        pst.executeUpdate();
        success = true;

    } catch (Exception e) {
        e.printStackTrace(); // Print the stack trace for debugging
        // Handle any exceptions here
    } finally {
        // Close resources
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print the stack trace for debugging
            // Handle any exceptions here
        }
    }
    return success;
}


    public  String[] getItemIds() {
    try {
        String sql = "SELECT book_id FROM books";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        // Create an ArrayList to hold the book IDs
        ArrayList<String> bookIds = new ArrayList<>();

        // Add each book ID to the ArrayList
        while (rs.next()) {
            String bookId = rs.getString("book_id");
            bookIds.add(bookId);
        }

        // Convert ArrayList to array
        String[] idsArray = new String[bookIds.size()];
        idsArray = bookIds.toArray(idsArray);

        return idsArray;

    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any SQL exceptions here
        return new String[0]; // Return an empty array if an exception occurs
    }
}



    
public DefaultTableModel viewBooks() {
    DefaultTableModel tableModel = null;
    try {
        String sql = "SELECT book_id, category, name, price, author, isbn, publisher FROM books";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null)
                rs.close();
            if (pst != null)
                pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return tableModel;
}

 public boolean addAccount(String txtusername, String txtpassword, String txtrole) {
     boolean success = false;
     return success;
 
 };
 
  public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

