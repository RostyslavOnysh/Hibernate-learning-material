package org.example;

import org.example.dao.LiteraryFormatDao;
import org.example.dao.LiteraryFormatDaoImpl;
import org.example.model.LiteraryFormat;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        LiteraryFormatDao literaryFormat = new LiteraryFormatDaoImpl();
        List<LiteraryFormat> allFormats = literaryFormat.getAll();
        for (LiteraryFormat format : allFormats) {
            System.out.println(format);
        }
    }
}
