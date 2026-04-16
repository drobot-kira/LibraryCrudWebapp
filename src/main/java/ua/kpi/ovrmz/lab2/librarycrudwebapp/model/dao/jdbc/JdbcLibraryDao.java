package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.jdbc;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.LibraryDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.exception.DaoException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcLibraryDao extends AbstractJdbcDao<Library> implements LibraryDao {
    private static final String DELETE_LIBRARY_BY_ID = "DELETE FROM libraries WHERE id = ? ";
    private static final String INSERT_INTO_LIBRARIES = "INSERT INTO libraries (name, foundation_year) VALUES (?, ?) ";
    private static final String UPDATE_LIBRARY_BY_ID = "UPDATE libraries SET name = ?, foundation_year = ? WHERE id = ? ";
    private static final String SELECT_FROM_LIBRARIES = "SELECT * FROM libraries ";
    private static final String ORDER_BY_ID = "ORDER BY libraries.id ";
    private static final String WHERE_ID = "WHERE id = ? ";
    private static final String WHERE_NAME_LIKE = "WHERE name ILIKE ? ";
    private static final String COUNT_LIBRARIES = "SELECT COUNT(*) FROM libraries ";
    private static final String SELECT_PAGINATED = SELECT_FROM_LIBRARIES + ORDER_BY_ID + "LIMIT ? OFFSET ? ";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String FOUNDATION_YEAR = "foundation_year";

    public JdbcLibraryDao(Connection connection) {
        super(connection);
    }


    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_LIBRARIES + ORDER_BY_ID;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_LIBRARIES;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_LIBRARY_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_LIBRARY_BY_ID;
    }

    @Override
    protected Library getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getLibraryFromResultSet(resultSet);
    }
    static Library getLibraryFromResultSet(ResultSet resultSet) throws SQLException {
        return new Library.Builder().setId(resultSet.getInt(ID)).setName(resultSet.getString(NAME)).setFoundationYear(resultSet.getInt(FOUNDATION_YEAR)).build();
    }
    @Override
    protected void setIdForEntity(Library entity, int id) {
        entity.setId(id);
    }
    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Library entity)
            throws SQLException {
        query.setString(1 , entity.getName());
        query.setInt(2 , entity.getFoundationYear());
    }
    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Library entity) throws SQLException {
        query.setString(1, entity.getName());
        query.setInt(2 , entity.getFoundationYear());
        query.setInt(3 , entity.getId());
    }
    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_LIBRARIES + WHERE_ID;
    }

    @Override
    public List<Library> findByNameContaining(String namePart) {
        List<Library> result = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_LIBRARIES + WHERE_NAME_LIKE + ORDER_BY_ID)) {

            query.setString(1, "%" + namePart + "%");

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
        return COUNT_LIBRARIES;
    }

    @Override
    protected String getSelectAllPaginatedQuery() {
        return SELECT_PAGINATED;
    }
}