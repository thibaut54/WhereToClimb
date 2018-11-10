package org.thibaut.wheretoclimb.business.contract;

import org.springframework.data.domain.Page;
import org.thibaut.wheretoclimb.model.entity.Area;

public interface AreaManager {

	Area findById( Integer id );

	void saveArea( Area area );

	void deleteArea( Integer id );
}
