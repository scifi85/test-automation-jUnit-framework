package tools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.OrderItem;

public class OrderItemService {
	private static EntityManager entityManager = Persistence.createEntityManagerFactory(
			"persistence").createEntityManager();

	
	public static void save(OrderItem orderItem) {
		entityManager.getTransaction().begin();
		entityManager.persist(orderItem);
		entityManager.getTransaction().commit();
	}

	public static void delete(OrderItem orderItem) {
		entityManager.getTransaction().begin();
		entityManager.remove(orderItem);
		entityManager.getTransaction().commit();
	}
	
	public static OrderItem get(int id) {
		return entityManager.find(OrderItem.class, id);		
	}

	public static void update(OrderItem orderItem) {
		entityManager.getTransaction().begin();
		entityManager.merge(orderItem);
		entityManager.getTransaction().commit();
	}

	 public static List<OrderItem> getAll() {
		  
		 Query query =  entityManager.createQuery("select c from OrderItem c ");
		 return (List<OrderItem>)query.getResultList();
	 }
}
