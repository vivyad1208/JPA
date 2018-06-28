package hbm.persistence.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class MetadataService {


	public static void main(String[] args) {

		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

		/*
		serviceRegistryBuilder
		.applySetting("hibernate.connection.datasource", "myDS")
		.applySetting("hibernate.format_sql", "true")
		.applySetting("hibernate.use_sql_comments", "true")
		.applySetting("hibernate.hbm2ddl.auto", "create-drop");*/

		serviceRegistryBuilder
		.applySetting("hibernate.hbm2ddl.auto", "update")
		.applySetting("hibernate.connection.dialect", "org.hibernate.dialect.MySQLDialect")
		.applySetting("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
		.applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/spring")
		.applySetting("hibernate.connection.username", "root")
		.applySetting("hibernate.connection.password", "root");

		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		MetadataSources metadataSources = new MetadataSources(serviceRegistry);

		metadataSources.addAnnotatedClass( hbm.persistence.entity.Message.class );

		MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		Metadata metadata = metadataBuilder.build();
		System.out.println(metadata.getEntityBindings());

		try (
			SessionFactory sessionFactory = metadata.buildSessionFactory();
			Session session = sessionFactory.openSession();
		) {
			//assertEquals(metadata.getEntityBindings().size(), 1);

			Transaction tx = session.beginTransaction();

			session.persist(new hbm.persistence.entity.Message().setText("Hello World!"));
			tx.commit();
		}

	}

}
