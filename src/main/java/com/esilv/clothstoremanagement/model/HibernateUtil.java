package com.esilv.clothstoremanagement.model;

import com.esilv.clothstoremanagement.model.entity.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {

    @Getter
    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/shop?createDatabaseIfNotExist=true");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "1234");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.current_session_context_class", "thread");
            configuration.setProperties(props);
            configuration.addAnnotatedClass(Company.class).addAnnotatedClass(Product.class).addAnnotatedClass(Accessory.class)
                    .addAnnotatedClass(Cloth.class).addAnnotatedClass(Shoe.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}