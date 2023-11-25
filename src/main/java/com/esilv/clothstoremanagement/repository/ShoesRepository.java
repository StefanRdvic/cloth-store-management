package com.esilv.clothstoremanagement.repository;

import com.esilv.clothstoremanagement.model.Shoes;
import com.esilv.clothstoremanagement.model.HibernateUtil;

import java.util.List;

public class ShoesRepository implements ICrudRepository<Shoes> {
    @Override
    public List<Shoes> findAll() {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var l =s.createQuery("SELECT a FROM Shoes a", Shoes.class).getResultList();
        s.getTransaction().commit();
        return l;
    }

    @Override
    public Shoes find(Long id) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var c = s.get(Shoes.class, id);
        s.getTransaction().commit();
        return c;
    }

    @Override
    public Shoes save(Shoes c) {
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
    public void delete(Shoes c) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        s.remove(c);
        s.getTransaction().commit();
    }
}
