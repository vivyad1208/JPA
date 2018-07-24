package hbm.persistence.service;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hbm.persistence.entity.Course;
import hbm.persistence.entity.Student;

public class MappingManyToManyCourseStudent {

	public static void main(String[] args) {
		List list = getSingleCourseAsList();
		System.out.println(list);
	}


	public void enrollStudentToCourse() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try{
			transaction = session.beginTransaction();

			@SuppressWarnings("unchecked")
			Query<Student> queryStudent = session.createNamedQuery("getStudentByName");
			queryStudent.setParameter("name", "Priyanka");
			Student student = queryStudent.getSingleResult();

			// Get Single Result
			@SuppressWarnings("unchecked")
			Query<Course> queryCourse = session.createNamedQuery("getCourseByName");
			queryCourse.setParameter("courseName", "Database");
			Course course = queryCourse.getSingleResult();			

			System.out.println(student);
			System.out.println(course);

			student.setCourses(Arrays.asList(course));
			session.persist(course);
			// session.save(course); 
			transaction.commit();
		}
		catch(Exception ex) {
			if(transaction!=null && transaction.isActive())
				transaction.rollback();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}


	public static List<Course> getSingleCourseAsList() {
		try (
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
		) {
			@SuppressWarnings("unchecked")
			Query<Course> queryCourse = session.createNamedQuery("getCourseByName");
			queryCourse.setParameter("courseName", "Database");
			Course course = queryCourse.getSingleResult();
			return Arrays.asList(course);
		}
	}
}
