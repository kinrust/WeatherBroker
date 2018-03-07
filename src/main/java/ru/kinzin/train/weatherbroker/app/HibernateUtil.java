package ru.kinzin.train.weatherbroker.app;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil
{
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            if (sessionFactory == null)
            {
                try {
                    Class.forName("org.hsqldb.jdbc.JDBCDriver" );
                } catch (Exception e) {
                    System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
                    e.printStackTrace();
                }

                Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
                configuration.addAnnotatedClass(ru.kinzin.train.weatherbroker.model.Weather.class);
                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                serviceRegistryBuilder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            return sessionFactory;
        } catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        getSessionFactory().close();
    }
}
