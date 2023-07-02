package org.example.dao.book;

import org.example.model.Author;
import org.example.model.Book;
import org.example.model.LiteraryFormat;
import org.example.util.ConnectionUtil;
import org.w3c.dom.ls.LSException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public Book create(Book book) {
        String InsertRequest = "INSERT INTO books (tittle,price,literary_format_id)" +
                "VALUES (?,?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement createBookStatement =
                     connection.prepareStatement(InsertRequest, Statement.RETURN_GENERATED_KEYS)) {
            createBookStatement.setString(1, book.getTitle());
            createBookStatement.setBigDecimal(2, book.getPrice());
            createBookStatement.setLong(3, book.getLiteraryFormat().getId());
            createBookStatement.executeUpdate();
            ResultSet generatedKeys = createBookStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1, Long.class);
                book.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can`t create insert format to DB", e);
        }
        return book;
    }

    @Override
    public Book get(Long id) {
        String selectRequest = "SELECT b.id AS book_id, " +
                "tittle, " +
                "price, " +
                "lf.id as literary_format_id, " +
                "lf.format " +
                "FROM books b " +
                "JOIN literary_formats lf " +
                "ON b.literary_format_id = lf.id " +
                "WHERE b.id = 3; ";
        Book book = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement getFormatsStatment = connection.prepareStatement(
                     selectRequest)) {
            getFormatsStatment.setLong(1, id);
            ResultSet resultSet = getFormatsStatment.executeQuery();
            if (resultSet.next()) {
                book = parseBookWithLiteraryFormatFromResulSet(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Can`t get formats from DB", e);
        }
        if (book != null) {
            book.setAuthors(getAuthorForBook(id));
        }
        return book;
    }

    private Book parseBookWithLiteraryFormatFromResulSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setTitle(resultSet.getString("tittle"));
        book.setPrice(resultSet.getBigDecimal("price"));
        LiteraryFormat literaryFormat = new LiteraryFormat();
        literaryFormat.setId(resultSet.getObject("literary_format_id", Long.class));
        literaryFormat.setFormat(resultSet.getString("format"));
        book.setLiteraryFormat(literaryFormat);
        book.setId(resultSet.getObject("id", Long.class));
        return book;
    }

    private List<Author> getAuthorForBook(Long bookId) {
        String getAllAuthorsForBookRequest = "SELECT id,name,lastname FROM authors a "
                + "JOIN books_authors ba "
                + "ON  a.id = ba.author_id "
                + "WHERE ba.book_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement getAllAuthorStatement =
                     connection.prepareStatement(getAllAuthorsForBookRequest)) {
            getAllAuthorStatement.setLong(1,bookId);
            ResultSet resultSet = getAllAuthorStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(parseAuthorsFromResultSet(resultSet));
            }
            return authors;
        }catch (SQLException e) {
            throw new RuntimeException("Can`t find authors in DB by book bookId" + bookId,e);
        }
    }

    private Author parseAuthorsFromResultSet (ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getObject("id",Long.class));
        author.setName(resultSet.getString("name"));
        author.setLastname(resultSet.getString("lastname"));
        return author;
    }
}
