/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author asillero
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry sr;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration conf=new Configuration();
            conf.configure();
            sr= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sessionFactory=conf.buildSessionFactory(sr);
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void close(){
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(sr);
    }
}
