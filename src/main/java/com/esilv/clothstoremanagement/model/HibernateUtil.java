package com.esilv.clothstoremanagement.model;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/shop?createDatabaseIfNotExist=true");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "91502");
            props.put("hibernate.hbm2ddl.auto", "create");
            props.put("hibernate.current_session_context_class", "thread");
            configuration.setProperties(props);
            configuration.addAnnotatedClass(Company.class).addAnnotatedClass(Product.class).addAnnotatedClass(Accessory.class)
                    .addAnnotatedClass(Cloth.class).addAnnotatedClass(Shoes.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Session s=sessionFactory.getCurrentSession();
        Company e= Company.builder().sells(1).buys(1).capital(1).build();
        s.beginTransaction();
        s.persist(e);
        s.getTransaction().commit();
        s.close();

    }
}