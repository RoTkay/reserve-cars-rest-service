package edu.daos;

import java.util.List;


public interface Dao<T> {
    List<T> findAll();
    T findOne(int id);
    void save(T entity);
    T update(T entity);
    void delete(int id);
    void deleteAll();
}
