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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VanAnh
 */
public class SQLServerDataProvider {

    private Connection connection;
    private String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=QL_FashionShop;encrypt=true;trustServerCertificate=true;";
    private String username = "sa";
    private String password = "123";

    public void open() {

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Could not connect to database.");
        }
        return connection;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement sm = connection.createStatement();
            rs = sm.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int n = -1;
        try {
            Statement sm = connection.createStatement();
            n = sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void commitTransaction() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
