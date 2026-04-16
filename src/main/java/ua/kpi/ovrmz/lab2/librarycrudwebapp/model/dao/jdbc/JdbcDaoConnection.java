package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.jdbc;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoConnection;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoConnection implements DaoConnection {
    private Connection connection;
    private boolean inTransaction = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    @Override
    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            inTransaction = false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    @Override
    public void rollback() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            inTransaction = false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    @Override
    public void close() {
        if(inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    Connection getConnection() {
        return connection;
    }
}