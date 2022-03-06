/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alberto
 */
public class LoginConnection {
    public Connection databaseLink;
    
    public Connection getConnection(){
        String databaseName = "sql3477143";
        String databaseUser = "";
        String databasePassword = "";
        String url = "jdbc:mysql://sql3.freesqldatabase.com/" + databaseName;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return databaseLink;
    }
    
}
