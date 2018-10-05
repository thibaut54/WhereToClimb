package org.thibaut.wheretoclimb.business.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AreaManager;
import org.thibaut.wheretoclimb.model.entity.Area;

@Component
public class AreaManagerImpl extends AbstractManager implements AreaManager {


	@Override
	public Page< Area > searchArea( int page, int size, String keyword ){
		Page< Area > areas =
				getDaoFactory().getAreaRepository().searchArea(
						"%"+keyword.toLowerCase()+"%", PageRequest.of( page, size ) );
		return areas;
	}
}
