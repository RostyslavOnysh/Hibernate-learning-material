package org.example;

import org.example.dao.LiteraryFormatDao;
import org.example.dao.LiteraryFormatDaoImpl;
import org.example.model.LiteraryFormat;

public class Main {
    public static void main(String[] args) {
       LiteraryFormat format = new LiteraryFormat();
        LiteraryFormatDao literaryFormat = new LiteraryFormatDaoImpl();
        format.setFormat("novels");
      //  LiteraryFormatDao literaryFormat = new LiteraryFormatDaoImpl();
       LiteraryFormat savedFormat = literaryFormat.create(format);
       // System.out.println(savedFormat);

     System.out.println(literaryFormat.delete(savedFormat.getId()));

        literaryFormat.getAll().forEach(System.out::println);
    }
}
