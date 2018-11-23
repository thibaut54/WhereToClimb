package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.consumer.contract.DaoFactory;

import javax.persistence.EntityManager;

@Component
public abstract class AbstractManager {

	private DaoFactory daoFactory;

	private EntityManager em;

	public DaoFactory getDaoFactory( ) {
		return daoFactory;
	}

	@Autowired
	public void setDaoFactory( DaoFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	public EntityManager getEm( ) {
		return em;
	}

	@Autowired
	public void setEm( EntityManager em ) {
		this.em = em;
	}

}