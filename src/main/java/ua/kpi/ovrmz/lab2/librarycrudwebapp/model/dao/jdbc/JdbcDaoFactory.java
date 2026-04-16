package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.jdbc;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.BookDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoConnection;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoFactory;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.LibraryDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.exception.DaoException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private final DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/library_db");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public LibraryDao createLibraryDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcLibraryDao(sqlConnection);
    }

    @Override
    public BookDao createBookDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcBookDao(sqlConnection);
    }
}