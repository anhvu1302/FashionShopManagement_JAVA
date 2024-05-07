/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VanAnh
 */
public class SQLServerDataProvider {
//   public static void main(String[] args) {
//        // JDBC URL for SQL Server, change accordingly
//        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=QL_FashionShop;encrypt=true;trustServerCertificate=true;";
//        String username = "sa";
//        String password = "123";
//
//        try {
//            // Load the SQL Server JDBC driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            
//            // Establish the connection
//            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//            
//            // Connection successful, do something...
//            System.out.println("Connected to the database.");
//            
//            // Remember to close the connection when done
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Error: SQL Server JDBC driver not found.");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("Error: Failed to connect to the database.");
//            e.printStackTrace();
//        }
//    }
   private Connection connection;
    public void open(){
        
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=QL_FashionShop;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "123";
        
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void close(){
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String sql){
        ResultSet rs= null;
        try {
            Statement sm= connection.createStatement();
            rs= sm.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public int executeUpdate(String sql){
        int n=-1;
        try {
            Statement sm= connection.createStatement();
            n=sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
}
