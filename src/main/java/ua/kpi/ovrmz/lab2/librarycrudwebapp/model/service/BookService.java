package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.BookDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoConnection;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoFactory;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final BookService INSTANCE = new BookService();
    }

    public static BookService getInstance() {
        return Holder.INSTANCE;
    }

    public void create(Book book) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try{
                BookDao dao = daoFactory.createBookDao(connection);
                connection.begin();
                dao.create(book);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new ServiceException(e);
            }
        }
    }

    public void update(Book book) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try{
                connection.begin();
                BookDao dao = daoFactory.createBookDao(connection);
                dao.update(book);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new ServiceException(e);
            }
        }
    }

    public void delete(int bookId) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try {
                connection.begin();
                BookDao dao = daoFactory.createBookDao(connection);
                dao.delete(bookId);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new ServiceException(e);
            }
        }
    }

    public Optional<Book> findById(Integer bookId) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try {
                BookDao dao = daoFactory.createBookDao(connection);
                return dao.find(bookId);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
    }

    public List<Book> findAll(int limit, int offset) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try {
                BookDao dao = daoFactory.createBookDao(connection);
                return dao.findAll(limit, offset);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
    }

    public long count() {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try{
                BookDao dao = daoFactory.createBookDao(connection);
                return dao.count();
            } catch(Exception e) {
                throw new ServiceException(e);
            }       }
    }

    public List<Book> findByTitleOrAuthorContaining(String searchString) {
        try(DaoConnection connection = daoFactory.getConnection()) {
            try{
                BookDao dao = daoFactory.createBookDao(connection);
                return dao.findByTitleOrAuthorContaining(searchString);
            } catch(Exception e) {
                throw new ServiceException(e);
            }
        }


        }
}