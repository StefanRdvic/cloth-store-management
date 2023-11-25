package com.esilv.clothstoremanagement.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class HibernateUtil {

    @Getter
    private static SessionFactory sessionFactory = null;

    static {
        try {
            sessionFactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            log.error("hibernate.cfg.xml misconfigured", new Throwable("Database Connection not established"));
        }
    }
}