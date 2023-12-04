package com.esilv.clothstoremanagement.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class HibernateUtil {

    @Getter(lazy = true)
    @Accessors(fluent = true)
    private final static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
}