package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Crag;

public interface CragManager {

	Crag findCragById( Integer id );

//	Page< Crag > searchCragByNameAndCountryAndRegionAndDepartment( int page, int size, String name, String country, String region, String department );

//	Page< Crag > searchCragByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject , String name, String country, String region, String department, String city );

	void saveCrag( Crag crag );

	void deleteCrag( Integer id );
}
