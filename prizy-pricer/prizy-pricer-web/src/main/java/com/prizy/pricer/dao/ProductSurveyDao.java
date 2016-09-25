package com.prizy.pricer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.prizy.pricer.entities.ProductSurvey;
import com.prizy.pricer.entitybroker.PrizypricerEntityBroker;

public class ProductSurveyDao {
	private EntityManager em;

	public ProductSurveyDao() {
		em = PrizypricerEntityBroker.getEntityManager();
	}

	public List<ProductSurvey> getAllItemsForProductId(Integer productId) {
		Query q = em.createQuery("SELECT productSurvey FROM ProductSurvey productSurvey where productSurvey.product.productId = " + productId + " ORDER BY productSurvey.surveyId");

		return (List<ProductSurvey>) q.getResultList();
	}

	public ProductSurvey getSingleItem(Integer surveyId) {
		Query q = em.createQuery("SELECT productSurvey FROM ProductSurvey productSurvey WHERE productSurvey.surveyId = " + surveyId);
		return (ProductSurvey) q.getSingleResult();
	}

	public Integer selectMaxSurveyId() {
		Query q = em.createQuery("SELECT MAX(productSurvey.surveyId) FROM ProductSurvey productSurvey");
		return (Integer) q.getSingleResult();
	}

	public void addProductSurvey(ProductSurvey productSurvey) {
		em.getTransaction().begin();
		em.persist(productSurvey);
		em.flush();
		em.getTransaction().commit();
	}

	public void updateProductSurvey(ProductSurvey productSurvey) {

		em.getTransaction().begin();
		em.merge(productSurvey);
		em.getTransaction().commit();
	}

	public void deleteProductSurvey(Integer surveyId) {
		ProductSurvey productSurvey = em.find(ProductSurvey.class, surveyId);

		em.getTransaction().begin();
		em.remove(productSurvey);
		em.getTransaction().commit();
	}
}
