package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Route;

public interface RouteManager {

	Route findRouteById( Integer id );

	void saveRoute( Route route );

	void deleteRoute( Integer id );
}
