package com.esilv.clothstoremanagement.repository;

import com.esilv.clothstoremanagement.model.Cloth;
import com.esilv.clothstoremanagement.model.HibernateUtil;

import java.util.List;

public class ClothRepository implements ICrudRepository<Cloth> {
    @Override
    public List<Cloth> findAll() {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var l =s.createQuery("SELECT a FROM Cloth a", Cloth.class).getResultList();
        s.getTransaction().commit();
        return l;
    }

    @Override
    public Cloth find(Long id) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var c = s.get(Cloth.class, id);
        s.getTransaction().commit();
        return c;
    }

    @Override
    public Cloth save(Cloth c) {
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
    public void delete(Cloth c) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        s.remove(c);
        s.getTransaction().commit();
    }
}
