package com.esilv.clothstoremanagement.model.repository;

import java.util.List;

/**
 * This interface represents a crud (Create, Read, Update, Delete) repository
 * author: Stefan Radovanovic
 * author: Yannick li
 */
public interface CrudRepository<T> {
    /**
     * This method returns all items from the database with pagination and searchValue filter
     * @param pageSize the number of items per page
     * @param pageNumber the page number
     * @param searchValue the search value
     * @return a list of items
     */
    List<T> findAll(int pageSize, int pageNumber, String searchValue);

    /**
     * This method returns all items from the database with pagination
     * @param pageSize the number of items per page
     * @param pageNumber the page number
     * @return a list of items
     */
    default List<T> findAll(int pageSize, int pageNumber){
        return findAll(pageSize, pageNumber, null);
    }

    /**
     * This method returns all items from the database
     * @return a list of items
     */
    default List<T> findAll(){
        return findAll(Integer.MAX_VALUE, 0);
    }

    /**
     * This method returns the first item from the database
     * @return an item
     */
    T findFirst();

    /**
     * This method updates an item in the database
     * @param item the item to update
     */
    void update(T item);

    /**
     * This method saves an item in the database
     * @param item the item to save
     */
    void save(T item);

    /**
     * This method deletes an item from the database
     * @param item the item to delete
     */
    void delete(T item);

    /**
     * This method returns the number of items in the database with searchValue filter
     * @param searchValue the search value
     * @return the number of items
     */
    long count(String searchValue);

    /**
     * This method returns the number of items in the database
     * @return the number of items
     */
    default long count(){
        return count(null);
    }
}
