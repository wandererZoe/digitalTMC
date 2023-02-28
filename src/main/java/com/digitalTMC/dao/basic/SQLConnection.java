package com.digitalTMC.dao.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SQLConnection {
    private final String user;
    private final String password;
    private final String driver;
    private final String url;

    public SQLConnection() {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        this.driver = bundle.getString("driver");
        this.url = bundle.getString("url");
        this.user = bundle.getString("user");
        this.password = bundle.getString("password");
    }

    public Connection connect() {
        //create connection
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            return connection;
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("DISCONNECT!");
            return null;
        }
    }

    public void disconnect(Connection conn){
        try {
            conn.close();
        } catch (SQLException e){
            System.out.println("SQL cannot disconnect");
        }
    }
}
