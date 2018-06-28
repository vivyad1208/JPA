package hbm.persistence.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hbm.persistence.entity.Employee;

public class ServiceAdvanced {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		Query<Employee> query = session.createNamedQuery("INACTIVE");
		//query.setParameter("active", 1);

		for (Object object : query.getResultList())
			System.out.println(object);

		session.close();
		sessionFactory.close();
	}
}
