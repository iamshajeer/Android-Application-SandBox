/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shajeer
 */
public class DbConnection {

    private final String DB_NAME = "aa_sandbox";
    private final String USER_NAME = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mysql://localhost/aa_sandbox";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    Connection connection;
    Statement statement;
    ResultSet result;

    public DbConnection() {
        if (creatConnction()) {
            
            System.out.println("connected");

        }
    }

    public boolean creatConnction() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void editSettings(int id, String value) throws SQLException {
        
        try{
            
            String query = "UPDATE settings SET value='"+value+"' WHERE id="+id;
            System.out.println(statement.executeUpdate(query)+" affected!");
            connection.close();
            
        }catch(Exception e){
            connection.close();
            e.getMessage();
        
        }
    }

    public String getSettings(int id) throws SQLException {
        String value = null;
        try {
            String query = "SELECT value FROM settings WHERE id="+id;
            result = statement.executeQuery(query);

            while (result.next()) {
                value = result.getString("value");
                connection.close();
                return value;
            }

        } catch (Exception e) {
            connection.close();
            e.getMessage();
        }
        return null;
    }
}
