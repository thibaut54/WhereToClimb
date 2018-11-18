package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Area;

public interface AreaManager {

	Area findAreaById( Integer id );

	void saveArea( Area area );

	void deleteArea( Integer id );

	Area createArea( Area area );
}
