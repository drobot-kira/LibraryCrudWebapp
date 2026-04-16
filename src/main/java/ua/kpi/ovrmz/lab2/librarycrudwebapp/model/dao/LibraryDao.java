package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;

import java.util.List;

public interface LibraryDao extends GenericDao<Library>{
    List<Library> findByNameContaining(String namePart);
}