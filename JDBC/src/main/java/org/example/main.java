package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection("jdbc:localhost:3306/library_db");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can`t load JDBC Driver for MySQL",e);
        } catch (SQLException throwables) {
            throw new RuntimeException("Can`t create connection to Database",throwables);
        }
    }
}
