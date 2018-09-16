package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.consumer.contract.DaoFactory;

@Component
public abstract class AbstractManager {

	private DaoFactory daoFactory;

	public DaoFactory getDaoFactory( ) {
		return daoFactory;
	}

	@Autowired
	public void setDaoFactory( DaoFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
}
