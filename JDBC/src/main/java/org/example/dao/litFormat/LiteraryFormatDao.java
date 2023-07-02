package org.example.dao.litFormat;

import org.example.model.Book;
import org.example.model.LiteraryFormat;

import java.util.List;
import java.util.Optional;

public interface LiteraryFormatDao {
    List<LiteraryFormat> getAll();

    LiteraryFormat create(LiteraryFormat format);

    boolean delete(Long id);
}
