package com.revature.sealTheDeal.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.models.User;
import com.revature.sealTheDeal.models.WeddingUser;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;

	public static Session getSession() throws IOException {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration();
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream("hibernate.properties"));

			// Add properties to our configuration
			configuration.setProperties(props);
			// ONE ADDITIONAL STEP I NEED TO INCLUDE
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Guest.class);
			configuration.addAnnotatedClass(WeddingUser.class);
			configuration.addAnnotatedClass(Employee.class);
			configuration.addAnnotatedClass(Booking.class);
			

			// ServiceRegistry
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		if(session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void closeSession() {
		session.close();
		session = null;
	}
}
