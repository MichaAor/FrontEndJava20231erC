package com.cybrixsystems.backjsp.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn = null;

    static {
        try {
            String url = "jdbc:mysql://localhost:3306/bdjsp" ;
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
              e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        return conn;
    }
}
