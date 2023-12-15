package com.esilv.clothstoremanagement.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class is an util providing a session factory
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {

    @Getter(lazy = true)
    @Accessors(fluent = true)
    private final static SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();
}