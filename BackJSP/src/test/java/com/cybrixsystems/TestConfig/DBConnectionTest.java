package com.cybrixsystems.TestConfig;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DBConnectionTest {

    @Test
    void dbConnectionSuccessful_test(){
        //Test Connection
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "";

        assertDoesNotThrow(()->{
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Successfully!!");
        });
    }

    @Test
    void dbConnectionFailed_test(){
        //Test Connection
        String url = "jdbc:mysql://localhost:3310/";
        String username = "root";
        String password = "";

        assertThrows(SQLException.class,()->{
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Successfully!!");
        });
    }
}
