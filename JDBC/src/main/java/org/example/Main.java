package org.example;

import org.example.model.LiteraryFormat;
import org.example.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionUtil.getConnection();
        Statement getAllFormatsStatment = null;
        try {
            getAllFormatsStatment = connection.createStatement();
            ResultSet resultSet = getAllFormatsStatment
                    .executeQuery("SELECT * FROM literary_formats");
            while (resultSet.next()) {
                String format = resultSet.getString("format");
                Long id = resultSet.getObject("id", Long.class);
                LiteraryFormat literaryFormat = new LiteraryFormat();
                literaryFormat.setId(id);
                literaryFormat.setFormat(format);
                System.out.println(literaryFormat);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can`t get all formats from DB", e);
        }

    }
}
