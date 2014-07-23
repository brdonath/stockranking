package com.dontah.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Bruno on 19/07/14.
 */
public class SessionDAO{

    private static Session session;

    private static SessionFactory configureSessionFactory() throws HibernateException {

        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configure
                        .getProperties())
                .buildServiceRegistry();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession() {
        if (session == null) {
            session = configureSessionFactory().openSession();
        }
        return session;
    }

}
