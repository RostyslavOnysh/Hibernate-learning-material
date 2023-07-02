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
        insertAuthors(book);
        return book;


    }

    private void insertAuthors(Book book) {
        String insertAuthorQuery = "INSERT INTO books_authors "
                + "(book_id, author_id)"
                + "VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement addAuthorToBookStatement = connection.prepareStatement(insertAuthorQuery)) {
            addAuthorToBookStatement.setLong(1, book.getId());
            for (Author author : book.getAuthors()) {
                addAuthorToBookStatement.setLong(2, author.getId());
                addAuthorToBookStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can`t insert authors to book" + book, e);
        }
    }

    @Override
    public Book update(Book book) {
        /*
        1. update books fields
        2. delete all relations in books_author where bookId = book.getId()
         головне не видалити абсолютно всі відошення з табличкою books_authors
        3. add new retalions to the books authors table
        4.оновлюємо всі поля які є в книзі (title etc.),потім видаляємо всі данні з таблички books_authors
        для нашої конкретної книги, і потім вже додати нові звязки в ту саму табличку
         */

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
                "WHERE b.id = ? AND b.is_deleted = false;";
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
            getAllAuthorStatement.setLong(1, bookId);
            ResultSet resultSet = getAllAuthorStatement.executeQuery();
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(parseAuthorsFromResultSet(resultSet));
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t find authors in DB by book bookId" + bookId, e);
        }
    }

    @Override
    public boolean delete(Long bookId) {
        String deleteQury = "UPDATE books SET is_deleted = TRUE WHERE id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement softDeleteBookStatement =
                     connection.prepareStatement(deleteQury)) {
            softDeleteBookStatement.setLong(1, bookId);
            int numberOFDeletedRows = softDeleteBookStatement.executeUpdate();
            return numberOFDeletedRows != 0;
        }catch (SQLException e) {
            throw new RuntimeException("Cant delete with id = " + bookId,e);
        }
    }

    private Author parseAuthorsFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getObject("id", Long.class));
        author.setName(resultSet.getString("name"));
        author.setLastname(resultSet.getString("lastname"));
        return author;
    }
}
