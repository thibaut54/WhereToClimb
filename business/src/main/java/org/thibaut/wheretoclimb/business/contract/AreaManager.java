package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.Area;

import java.util.List;

public interface AreaManager {

	Area findAreaById( Integer id );

	void saveArea( Area area );

	void deleteArea( Integer id );

	Area createArea( Area area );

	List<Area> findAreasByAtlasId( Integer atlasId );
}
