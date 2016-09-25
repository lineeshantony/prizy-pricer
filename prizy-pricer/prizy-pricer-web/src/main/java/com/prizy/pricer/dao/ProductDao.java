package com.prizy.pricer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.prizy.pricer.entities.Product;
import com.prizy.pricer.entitybroker.PrizypricerEntityBroker;

public class ProductDao {
	private EntityManager em;

	public ProductDao() {
		em = PrizypricerEntityBroker.getEntityManager();
	}

	public List<Product> getAllItems() {
		Query q = em.createQuery("SELECT product FROM Product product ORDER BY product.productId");

		return (List<Product>) q.getResultList();
	}

	public Product getSingleItem(Integer productId) {
		Query q = em.createQuery("SELECT product FROM Product product WHERE product.productId = " + productId);
		return (Product) q.getSingleResult();
	}

	public Product getSingleItemWithBarCode(String barCode) {
		Query q = em.createQuery("SELECT product FROM Product product WHERE product.productBarCode = '" + barCode + "'");
		return (Product) q.getSingleResult();
	}

	public List<Product> getItemsWithBarCode(String barCode) {
		Query q = em.createQuery("SELECT product FROM Product product WHERE product.productBarCode like '%" + barCode + "%'");
		return (List<Product>) q.getResultList();
	}

	public void addProduct(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.flush();
		em.getTransaction().commit();
	}

	public void updateProduct(Product product) {

		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();
	}

	public void deleteItem(Integer productId) {
		Product product = em.find(Product.class, productId);

		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
	}

}
