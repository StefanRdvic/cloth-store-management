package com.esilv.clothstoremanagement.model.repository;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    T find(Long id);
    void save(T item);
    void delete(T item);
}
