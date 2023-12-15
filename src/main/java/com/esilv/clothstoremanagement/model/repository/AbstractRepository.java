package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Page;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.List;

/**
 * This class is an abstraction for all repositories
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Slf4j
public abstract class AbstractRepository<T> implements CrudRepository<T>{

    @Override
    public List<T> findAll(int pageSize, int pageNumber, String searchValue) {
        return HibernateUtil.sessionFactory().fromTransaction(
                session ->
                        (searchValue == null || searchValue.isEmpty() ?
                                createSelectionQuery(session) :
                                createSelectionQuery(session, searchValue))
                        .setPage(Page.page(pageSize, pageNumber))
                        .getResultList());
    }

    @Override
    public T findFirst() {
        return HibernateUtil.sessionFactory().fromTransaction(
                session -> createSelectionQuery(session).setMaxResults(1).uniqueResult()
        );
    }

    @Override
    public void update(T item) {
        HibernateUtil.sessionFactory().inTransaction(session -> session.merge(item));
    }

    @Override
    public void save(T item) {
        HibernateUtil.sessionFactory().inTransaction(session -> session.persist(item));
    }

    @Override
    public void delete(T item) {
        HibernateUtil.sessionFactory().inTransaction(session -> session.remove(item));
    }

    @Override
    public long count(String searchValue) {
        return (long) HibernateUtil.sessionFactory().fromTransaction(
                session ->
                        (searchValue == null || searchValue.isEmpty() ?
                                createCountQuery(session) :
                                createCountQuery(session, searchValue))
                        .uniqueResult());
    }

    /**
     * This method is used to create a selection query
     * @param session the session
     * @return the selection query
     */
    protected abstract SelectionQuery<T> createSelectionQuery(Session session);

    /**
     * This method is used to create a selection query
     * @param session the session
     * @param searchValue the search value
     * @return the selection query
     */
    protected abstract SelectionQuery<T> createSelectionQuery(Session session, String searchValue);

    /**
     * This method is used to create a count query
     * @param session the session
     * @return the count query
     */
    protected abstract Query<T> createCountQuery(Session session);

    /**
     * This method is used to create a count query
     * @param session the session
     * @param searchValue the search value
     * @return the count query
     */
    protected abstract Query<T> createCountQuery(Session session, String searchValue);

}
