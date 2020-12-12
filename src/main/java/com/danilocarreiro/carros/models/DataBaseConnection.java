/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danilocarreiro.carros.models;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DANILODOSSANTOSCARRE
 */
public class DataBaseConnection {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/carros";
    private final String user = "root";
    private final String password = "";
    private java.sql.Connection conn; 
    private static DataBaseConnection instance;

    private DataBaseConnection(){
       this.init();
    }
    
    public void init(){
        try
       {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url,this.user,this.password);
        }
        catch(Exception e)
        {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }
    
    public java.sql.Connection getConexaoVW(){
        return this.conn;
    }
    
    public static DataBaseConnection getInstance() throws SQLException{
        
        if(instance == null)
        {
            instance = new DataBaseConnection();
        }
        else if(instance.getConexaoVW().isClosed()){
            instance = new DataBaseConnection();
        }

        return instance;
    }
  
}
