package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao;

public interface DaoConnection extends AutoCloseable{
    void begin();
    void commit();
    void rollback();
    void close();
}