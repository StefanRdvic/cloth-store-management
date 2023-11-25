package com.esilv.clothstoremanagement.repository;

import java.util.List;

public interface ICrudRepository<T> {
    List<T> findAll();
    T find(Long id);
    T save(T item);
    void delete(T item);
}
