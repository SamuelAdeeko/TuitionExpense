package com.tuitionexpense.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	
	
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "jdbc:postgresql://my8weeksinstance.cjfjnd6bxxdf.us-west-1.rds.amazonaws.com:5432/postgres")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", "password").buildSessionFactory();
		}
		
		return sessionFactory.getCurrentSession();
	}
	
}
