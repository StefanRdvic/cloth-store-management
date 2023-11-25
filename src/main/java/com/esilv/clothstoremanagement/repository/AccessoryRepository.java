package com.esilv.clothstoremanagement.repository;

import com.esilv.clothstoremanagement.model.Accessory;
import com.esilv.clothstoremanagement.model.HibernateUtil;

import java.util.List;

public class AccessoryRepository implements ICrudRepository<Accessory>  {
    @Override
    public List<Accessory> findAll() {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var l =s.createQuery("SELECT a FROM Accessory a", Accessory.class).getResultList();
        s.getTransaction().commit();
        return l;
    }

    @Override
    public Accessory find(Long id) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        var c = s.get(Accessory.class, id);
        s.getTransaction().commit();
        return c;
    }

    @Override
    public Accessory save(Accessory c) {
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
    public void delete(Accessory c) {
        var s = HibernateUtil.getSession();
        s.beginTransaction();
        s.remove(c);
        s.getTransaction().commit();
    }
}
