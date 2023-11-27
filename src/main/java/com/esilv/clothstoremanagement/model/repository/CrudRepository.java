package com.esilv.clothstoremanagement.model.repository;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll(int pageSize, int pageNumber, String searchValue);

    default List<T> findAll(int pageSize, int pageNumber){
        return findAll(pageSize, pageNumber, null);
    }

    default List<T> findAll(){
        return findAll(Integer.MAX_VALUE, 0);
    }

    void update(T item);
    void save(T item);
    void delete(T item);

    long count(String searchValue);

    default long count(){
        return count(null);
    }
}
