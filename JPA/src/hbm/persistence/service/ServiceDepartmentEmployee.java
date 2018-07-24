package hbm.persistence.service;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hbm.persistence.entity.Course;
import hbm.persistence.entity.Student;

public class ServiceDepartmentEmployee {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Query<Student> queryStudent;
		Query<Course> queryCourse;

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			queryStudent = session.createNamedQuery("getStudentByName");
			queryStudent.setParameter("name", "Priyanka");
			Student student = queryStudent.getSingleResult();

			if(student!=null)
				throw new Exception();

			queryCourse = session.createNamedQuery("getCourseByName");
			queryCourse.setParameter("courseName", "Database");
			Course course = queryCourse.getSingleResult();			

			System.out.println(student);
			System.out.println(course);

			course.setStudents(Arrays.asList(student));
			session.update(course);
			transaction.commit();
		}
		catch(Exception ex) {
			if(transaction!=null && transaction.isActive())
				transaction.rollback();
			System.err.println(ex);
		}
		finally {
			session.close();
			sessionFactory.close();
		}
		
	}
}
