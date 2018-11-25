package org.thibaut.wheretoclimb.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AreaManager;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AreaManagerImpl extends AbstractManager implements AreaManager {


	@Override
	public Area findAreaById( Integer id ) {

		if ( id != null ) {
			Optional< Area > area = getDaoFactory( ).getAreaRepository( ).findById( id );
			if ( area.isPresent( ) ) {
				return area.get( );
			}
		}
		return null;
	}


	@Override
	public void saveArea( Area area ) {
		getDaoFactory( ).getAreaRepository( ).save( area );
	}


	@Override
	public void deleteArea( Integer id ) {
		getDaoFactory( ).getCommentRepository( ).deleteAll( findAreaById( id ).getComments( ) );
		getDaoFactory( ).getAreaRepository( ).deleteById( id );
	}


	@Override
	public Area createArea( Area area ) {
		getDaoFactory( ).getAreaRepository( ).save( area );
		return area;
	}


	@Override
	public List<Area> findAreasByAtlasId( Integer atlasId ){
		return getDaoFactory().getAreaRepository().findAreaByAtlasId( atlasId );
	}

}
