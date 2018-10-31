package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Area;

import java.util.List;

public interface AreaManager {
	Area findById( Integer id );

	Page< Area > searchAreaByNameAndCountryAndRegionAndDepartment( int page, int size, String name, String country, String region, String department );

//	Page< Area > searchArea( int page, int size, String keyword );
}
