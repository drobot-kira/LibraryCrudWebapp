package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.jdbc;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.BookDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.exception.DaoException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookDao extends AbstractJdbcDao<Book> implements BookDao {
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM books b ";
    private static final String INSERT_INTO_BOOKS = "INSERT INTO books (title, author, library_id) VALUES (?, ?, ?) ";

    private static final String SELECT_FROM_BOOKS = "SELECT b.id AS book_id, b.title AS book_title, b.author AS book_author, " +
            "l.id AS library_id, l.name AS library_name, l.foundation_year AS library_year " +
            "FROM books b " +
            "JOIN libraries l ON b.library_id = l.id ";
    private static final String WHERE_ID = "WHERE b.id = ? ";
    private static final String WHERE_TITLE_OR_AUTHOR_LIKE = "WHERE b.title ILIKE ? OR b.author ILIKE ? ";
    private static final String ORDER_BY_ID = "ORDER BY b.id ";

    private static final String UPDATE_BOOKS = "UPDATE books b SET title = ?, author = ?, library_id = ? ";

    private static final String COUNT_BOOKS = "SELECT COUNT(*) FROM books ";
    private static final String SELECT_PAGINATED = SELECT_FROM_BOOKS + ORDER_BY_ID + "LIMIT ? OFFSET ? ";

    private static final String BOOK_ID = "book_id";
    private static final String TITLE = "book_title";
    private static final String AUTHOR = "book_author";

    private static final String LIBRARY_ID = "library_id";
    private static final String LIBRARY_NAME = "library_name";
    private static final String LIBRARY_YEAR = "library_year";

    public JdbcBookDao(Connection connection) {
        super(connection);
    }

    static Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Library library = new Library.Builder()
                .setId(resultSet.getInt(LIBRARY_ID))
                .setName(resultSet.getString(LIBRARY_NAME))
                .setFoundationYear(resultSet.getInt(LIBRARY_YEAR))
                .build();

        return new Book.Builder()
                .setId(resultSet.getInt(BOOK_ID))
                .setTitle(resultSet.getString(TITLE))
                .setAuthor(resultSet.getString(AUTHOR))
                .setLibrary(library)
                .build();
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_BOOKS + ORDER_BY_ID;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_BOOKS;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_BOOKS + WHERE_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_BOOK_BY_ID + WHERE_ID;
    }

    @Override
    protected Book getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getBookFromResultSet(resultSet);
    }

    @Override
    protected void setIdForEntity(Book entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Book entity) throws SQLException {
        query.setString(1, entity.getTitle());
        query.setString(2, entity.getAuthor());
        query.setInt(3, entity.getLibrary().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Book entity) throws SQLException {
        query.setString(1, entity.getTitle());
        query.setString(2, entity.getAuthor());
        query.setInt(3, entity.getLibrary().getId());
        query.setInt(4, entity.getId());
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_BOOKS + WHERE_ID;
    }

    @Override
    public List<Book> findByTitleOrAuthorContaining(String searchString) {
        List<Book> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_BOOKS + WHERE_TITLE_OR_AUTHOR_LIKE + ORDER_BY_ID)) {
            query.setString(1, "%" + searchString + "%");
            query.setString(2, "%" + searchString + "%");

            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    protected String getCountQuery() {
        return COUNT_BOOKS;
    }

    @Override
    protected String getSelectAllPaginatedQuery() {
        return SELECT_PAGINATED;
    }
}