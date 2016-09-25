package com.prizy.pricer.entitybroker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PrizypricerEntityBroker {
	private static EntityManagerFactory factory;
	private static EntityManager em;

	private PrizypricerEntityBroker() {
	}

	public static EntityManager getEntityManager() {
		if (em == null) {
			factory = Persistence.createEntityManagerFactory("PrizyPricerOpenJPAConfiguration", System.getProperties());
			em = factory.createEntityManager();
		}
		return em;
	}

	public static void close() {
		em.close();
		factory.close();
	}

}
