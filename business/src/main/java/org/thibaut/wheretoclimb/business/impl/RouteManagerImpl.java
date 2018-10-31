package org.thibaut.wheretoclimb.business.impl;

import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.RouteManager;
import org.thibaut.wheretoclimb.model.entity.Crag;
import org.thibaut.wheretoclimb.model.entity.Route;

import java.util.Optional;

@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager {

	@Override
	public Route findById( Integer id ){

		if(id != null){
			Optional< Route > routeOpt =  getDaoFactory().getRouteRepository().findById( id );
			if(routeOpt.isPresent()){
				return routeOpt.get();
			}
		}
		return null;
	}
}
