package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public abstract class AbstractRepository<T> implements CrudRepository<T>{

    @Override
    public List<T> findAll() {
        return HibernateUtil.getSessionFactory().fromTransaction(
                session -> createSelectionQuery(session)
                        .getResultList());
    }

    @Override
    public T find(Long id) {
        return null;
    }

    @Override
    public void save(T item) {
        HibernateUtil.getSessionFactory().inTransaction(session -> session.persist(item));
    }

    @Override
    public void delete(T item) {
        HibernateUtil.getSessionFactory().inTransaction(session -> session.remove(item));
    }

    protected abstract SelectionQuery<T> createSelectionQuery(Session s);
}
