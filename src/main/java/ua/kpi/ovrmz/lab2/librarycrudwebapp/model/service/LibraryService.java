package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoConnection;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.DaoFactory;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.LibraryDao;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class LibraryService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public static LibraryService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final LibraryService INSTANCE = new LibraryService();
    }

    public void create(Library library) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try {
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                connection.begin();
                dao.create(library);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new ServiceException(e);
            }
        }
    }

    public void update(Library library) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try {
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                connection.begin();
                dao.update(library);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new ServiceException(e);
            }
        }
    }

    public void delete(int libraryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try {
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                connection.begin();
                dao.delete(libraryId);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw new ServiceException(e);
            }
        }
    }
    public Optional<Library> findById(Integer libraryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try{
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                return dao.find(libraryId);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
    }
    public List<Library> findAll(int limit, int offset) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try{
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                return dao.findAll(limit, offset);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
    }
    public long count() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try{
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                return dao.count();
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
    }

    public List<Library> findByName(String name) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            try{
                LibraryDao dao = daoFactory.createLibraryDao(connection);
                return dao.findByNameContaining(name);
            } catch (Exception e) {
                throw new ServiceException(e);
            }
        }
    }
}