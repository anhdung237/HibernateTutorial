package org.o7planning.tutorial.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	// Hibernate 5
	private static SessionFactory buildSessionFactory() {
		try {
			// Tao doi tuong ServiceRegistry tu hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			
			// Tao nguon sieu du lieu (metadata) tu ServiceRegistry
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
			
			return metadata.getSessionFactoryBuilder().build();
			
		} catch (Throwable ex) {
			System.err.println("Initial sessionfactory creation failed . " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void shutdown(){
		// giai phong cache va connection pools
		getSessionFactory().close();
	}
	
}
