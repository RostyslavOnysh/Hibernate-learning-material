package org.example;

import org.example.dao.LiteraryFormatDao;
import org.example.dao.LiteraryFormatDaoImpl;

public class Main {
    public static void main(String[] args) {
        LiteraryFormatDao literaryFormat = new LiteraryFormatDaoImpl();
        literaryFormat.getAll().forEach(System.out::println);
    }
}
