package org.thibaut.wheretoclimb.business.impl;

import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.RouteManager;
import org.thibaut.wheretoclimb.model.entity.Route;

import java.util.Optional;

@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager {

	@Override
	public Route findRouteById( Integer id ){

		if(id != null){
			Optional< Route > routeOpt =  getDaoFactory().getRouteRepository().findById( id );
			if(routeOpt.isPresent()){
				return routeOpt.get();
			}
		}
		return null;
	}


	@Override
	public void saveRoute( Route route ) {
		getDaoFactory().getRouteRepository().save( route );
	}


	@Override
	public void deleteRoute( Integer id ){
		getDaoFactory().getCommentRepository().deleteAll( findRouteById( id ).getComments() );
		getDaoFactory().getRouteRepository().deleteById( id );
	}
}
