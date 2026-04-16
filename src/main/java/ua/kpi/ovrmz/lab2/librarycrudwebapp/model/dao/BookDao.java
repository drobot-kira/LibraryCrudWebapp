package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;

import java.util.List;

public interface BookDao extends GenericDao<Book>{
    List<Book> findByTitleOrAuthorContaining(String searchString);
}