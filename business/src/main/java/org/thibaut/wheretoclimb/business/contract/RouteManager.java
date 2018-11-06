package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Route;

public interface RouteManager {
	Route findById( Integer id );

	Page< Route > searchRouteByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject , String name, String country, String region, String department, String city );
}
