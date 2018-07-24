package hbm.persistence.service;

import java.sql.Time;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hbm.persistence.entity.Course;
import hbm.persistence.entity.Department;
import hbm.persistence.entity.Employee;
import hbm.persistence.entity.EntityInterface;
import hbm.persistence.entity.Student;


public class StoreData {

	private String message = "";

	private static Department department = new Department();
	private static Employee employee = new Employee();

	private static Course course = new Course();
	private static Student student = new Student();

	enum Entities { DEPARTMENT, EMPLOYEE, COURSE, STUDENT }


	@SuppressWarnings("serial")
	public static void main(String[]args) {
		StoreData sd = new StoreData();
		boolean useXML = false;
		Entities entityType = Entities.EMPLOYEE;
		sd.storeData( useXML, entityType, new HashMap<String,Object>(){{
			put("name","JB"); put("age",02); put("departmentName","Borad of Directors");
		}});

		// Department -> put("departmentName","Management");
		// Employee -> put("name","Priyanka"); put("age",31); put("departmentName","Management");
		// Course -> put("startTime","10:00:00"); put("endTime","11:00:00");
		// Student -> put("name","Priyanka"); put("age",31);
	}


	@SuppressWarnings("unchecked")
	private void storeData(boolean useXML, Entities type, HashMap<String,Object> map) {
		if(!checkEntity(type))
			return;

		EntityInterface entity;

		try(
			SessionFactory factory = getSessionFactory(useXML);
			Session session = factory.openSession();
		) {
			Transaction transaction = session.beginTransaction();
	
			System.out.println("Configuration through "+message+"!");
	
			entity = EntityFactory.geEntity(type, map);

			if(type==Entities.EMPLOYEE) {
				Query<Department> query = session.getNamedQuery("GetDepartment");
				query.setParameter("departmentName", map.get("departmentName"));
				((Employee)entity).setDepartment(query.getSingleResult());
			}

			System.out.println(entity);

			session.saveOrUpdate(entity);
			session.flush();
	
			transaction.commit();
		}

		System.out.println("Trasaction completed successfully using "+message+"!");
	}


	private static boolean checkEntity(Entities type) {
		return (type == Entities.DEPARTMENT) || (type == Entities.EMPLOYEE) || (type == Entities.COURSE) || (type == Entities.STUDENT);
	}


	public int getMaxId(SessionFactory factory) {
		Session session = factory.openSession();
		Object id = session.getNamedQuery("MAX ID").getSingleResult();
		session.close();
		return (int)id;
	}


	private SessionFactory getSessionFactory(boolean useXML) {
		Configuration config = new Configuration();
		if(useXML) {
			config = config.configure("hibernate/xml/hibernate.cfg.xml");
			message = "XML";
		}
		else {
			config = config.configure();
			message = "Config";
		}
		return config.buildSessionFactory();
	}


	public static class EntityFactory {

		public static EntityInterface geEntity(Entities e, HashMap<String,Object> map) {
			if(e == Entities.DEPARTMENT) {
				department.setDepartmentName((String)map.get("departmentName"));
				department.setActive(1);
				return department;
			}
			if(e == Entities.EMPLOYEE) {
				employee.setName((String)map.get("name"));
				employee.setAge((Integer)map.get("age"));
				employee.setActive(1);
				return employee;
			}
			if(e == Entities.COURSE) {
				course.setCourseName("Java");
				course.setStartTime(Time.valueOf((String)map.get("startTime")));
				course.setEndTime(Time.valueOf((String)map.get("endTime")));
				course.setFees(12000);
				return course;
			}
			if(e == Entities.STUDENT) {
				student.setName((String)map.get("name"));
				student.setAge((Integer)map.get("age"));
				student.setActive(1);
				return student;
			}
			return null;
		}

	}


}
