package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ElementManager {
	Page searchElementByNameAndCountryAndRegionAndDepartmentAndCity( Pageable pageable, String objectType, String name, String country, String region, String department, String city  );
}
