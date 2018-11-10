package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AreaManager;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.QArea;
import org.thibaut.wheretoclimb.model.entity.QAtlas;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AreaManagerImpl extends AbstractManager implements AreaManager {


	@Override
	public Area findById( Integer id ){

		if(id != null){
			Optional< Area > area =  getDaoFactory().getAreaRepository().findById( id );
			if(area.isPresent()){
				return area.get();
			}
		}
		return null;
	}


	@Override
	public void saveArea( Area area ){
		getDaoFactory().getAreaRepository().save( area );
	}

	@Override
	public void deleteArea( Integer id ){
		getDaoFactory().getAreaRepository().deleteById( id );
	}


}
