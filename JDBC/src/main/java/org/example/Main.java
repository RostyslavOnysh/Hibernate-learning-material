package org.example;

import org.example.dao.BookDao;
import org.example.dao.BookDaoImpl;
import org.example.dao.LiteraryFormatDao;
import org.example.dao.LiteraryFormatDaoImpl;
import org.example.model.Book;
import org.example.model.LiteraryFormat;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        LiteraryFormatDao literaryFormatDao = new LiteraryFormatDaoImpl();
        LiteraryFormat format = new LiteraryFormat();
        format.setFormat("poema");
        LiteraryFormat savedFormat = literaryFormatDao.create(format);

        BookDao bookDao = new BookDaoImpl();
        Book book = new Book();
        book.setTitle("WIJ");
        book.setPrice(BigDecimal.valueOf(200));
        book.setLiteraryFormat(savedFormat); // Assign the savedFormat to the book's literaryFormat
        bookDao.create(book);
    }
}