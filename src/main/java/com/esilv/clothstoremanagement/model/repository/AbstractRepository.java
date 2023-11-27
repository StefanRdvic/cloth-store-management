package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Page;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;

import java.util.List;

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

    protected abstract SelectionQuery<T> createSelectionQuery(Session session);
    protected abstract SelectionQuery<T> createSelectionQuery(Session session, String searchValue);

    protected abstract Query<T> createCountQuery(Session session);

    protected abstract Query<T> createCountQuery(Session session, String searchValue);

}
