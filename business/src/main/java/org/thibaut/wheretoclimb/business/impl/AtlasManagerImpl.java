package org.thibaut.wheretoclimb.business.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.entity.Atlas;

//import org.springframework.data.repository.query.Param;

@Component
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {

	@Override
	public Page< Atlas > getAtlases( int page , int size ) {

		System.out.println( "In ATLASMANAGER, getAtlas() method" );

		Page<Atlas> atlases = getDaoFactory().getAtlasRepository().findAll( PageRequest.of( page, size ) );

		return atlases;
	}

	@Override
	public Page <Atlas> searchAtlas( int page, int size, String keyword ){
		Page<Atlas> atlases =
				getDaoFactory().getAtlasRepository().searchAtlas(
						"%"+keyword.toLowerCase()+"%",PageRequest.of( page, size ) );
		return atlases;
	}

	@Override
	public void deleteAtlas( Integer id ){
		getDaoFactory().getAtlasRepository().deleteById( id );
	}

	@Override
	public void saveAtlas( Atlas atlas ){
		getDaoFactory().getAtlasRepository().save( atlas );
	}
}
