package org.example.dao.book;

import org.example.model.Book;

public interface BookDao {
    Book create (Book book);

    Book get(Long id);

    boolean delete(Long id);

    Book update(Book book);  // return book
}
