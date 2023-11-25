package com.esilv.clothstoremanagement.repository;

import com.esilv.clothstoremanagement.model.Company;
import com.esilv.clothstoremanagement.model.HibernateUtil;

import java.util.List;


public class CompanyRepository implements ICrudRepository<Company> {
    @Override
    public List<Company> findAll() {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var l =s.createQuery("SELECT a FROM Company a", Company.class).getResultList();
        s.getTransaction().commit();
        return l;
    }

    @Override
    public Company find(Long id) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var c = s.get(Company.class, id);
        s.getTransaction().commit();
        return c;
    }

    @Override
    public Company save(Company c) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        if (c.getId() == null)
            s.persist(c);
        else
            c=s.merge(c);
        s.getTransaction().commit();
        return c;
    }

    @Override
    public void delete(Company c) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        s.remove(c);
        s.getTransaction().commit();
    }
}
