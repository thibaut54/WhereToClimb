package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Route;

public interface RouteManager {
	Route findById( Integer id );
}
