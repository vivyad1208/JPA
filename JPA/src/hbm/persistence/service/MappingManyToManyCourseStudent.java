package hbm.persistence.service;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hbm.persistence.entity.Course;
import hbm.persistence.entity.Student;

public class MappingManyToManyCourseStudent {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		Transaction transaction;
		Query<Student> queryStudent;
		Query<Course> queryCourse;

		try (
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
		)
		{
			transaction = session.beginTransaction();

			queryStudent = session.createNamedQuery("getStudentByName");
			queryStudent.setParameter("name", "Priyanka Manjrekar");
			Student student = queryStudent.getSingleResult();

			queryCourse = session.createNamedQuery("getCourseByName");
			queryCourse.setParameter("courseName", "Database");
			Course course = queryCourse.getSingleResult();			

			System.out.println(student);
			System.out.println(course);

			course.setStudents(Arrays.asList(student));
			session.update(course);
			transaction.commit();
		}
	}
}
