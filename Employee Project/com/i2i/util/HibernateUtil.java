package com.i2i.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This is a utility class for getting the hibernate session object.
 *
 * @author  Vishwaeaswaran M
 * @version 1.0 13 Sep 2022
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                sessionFactory = configuration.configure().buildSessionFactory(); 
            } catch (HibernateException hibernateException) {
                System.err.println(hibernateException);
            }
        }
        return sessionFactory;
    }
}