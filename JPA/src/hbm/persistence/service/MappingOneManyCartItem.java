package hbm.persistence.service;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hbm.persistence.entity.Cart;
import hbm.persistence.entity.Items;

public class MappingOneManyCartItem {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Session created");

			Transaction tx = session.beginTransaction();

			// Entities
			Cart cart = new Cart();
			cart.setCartName("Clothings");

			Items shirt = new Items();
			shirt.setItemName("shirt");
			shirt.setCart(cart);

			Items pant = new Items();
			pant.setItemName("pant");
			pant.setCart(cart);

			cart.setItems(new HashSet<Items>(Arrays.asList(shirt,pant)){});
			session.save(cart);
			session.save(pant);
			session.save(shirt);

			tx.commit();

			System.out.println("Cart ID=" + cart.getCartId());
			System.out.println("item1 ID=" + shirt.getItemId()
			  + ", Foreign Key Cart ID=" + shirt.getCart().getCartId());
			System.out.println("item2 ID=" + pant.getItemId()
			+ ", Foreign Key Cart ID=" + shirt.getCart().getCartId());

		}
		finally {
			if(session!=null)
				session.close();
			if(sessionFactory!=null)
				sessionFactory.close();
		}
	}
}
