package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.jdbc;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.GenericDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJdbcDao<E> implements GenericDao<E> {
    protected Connection connection;

    public AbstractJdbcDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getSelectAllQuery();
    protected abstract String getCreateQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract E getEntityFromResultSet(ResultSet resultSet) throws SQLException;
    protected abstract void setIdForEntity(E entity, int id);
    protected abstract void prepareStatementForInsert(PreparedStatement query, E entity) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement query, E entity) throws SQLException;
    protected abstract String getSelectByIdQuery();
    protected abstract String getCountQuery();
    protected abstract String getSelectAllPaginatedQuery();
    @Override
    public Optional<E> find(Integer id) {
        Optional<E> result = Optional.empty();
        try(PreparedStatement query =
                    connection.prepareStatement(getSelectByIdQuery())){
            query.setInt( 1 , id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
    @Override
    public List<E> findAll() {
        List<E> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(getSelectAllQuery())){
            while (resultSet.next()) {
                result.add( getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public void create(E entity) {
        try( PreparedStatement query =
                     connection.prepareStatement(getCreateQuery(),
                             Statement.RETURN_GENERATED_KEYS ) ){
            prepareStatementForInsert(query, entity);
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                setIdForEntity(entity, keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(E entity) {
        try (PreparedStatement query =
                     connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(query, entity);
            int count = query.executeUpdate();
            if (count != 1) {
                throw new DaoException();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query =
                     connection.prepareStatement(getDeleteQuery())) {
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public long count() {
        try (PreparedStatement query = connection.prepareStatement(getCountQuery())) {
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<E> findAll(int limit, int offset) {
        List<E> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(getSelectAllPaginatedQuery())) {

            query.setInt(1, limit);
            query.setInt(2, offset);

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}


