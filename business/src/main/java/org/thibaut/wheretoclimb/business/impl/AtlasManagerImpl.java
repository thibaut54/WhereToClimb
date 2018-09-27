package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.beans.Atlas;

import java.util.List;

@Component
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {

	@Override
//	@Autowired
	public Page< Atlas > getAtlases( int page , int size ) {

		System.out.println( "In ATLASMANAGER, getAtlas() method" );

//		List<Atlas> atlases = getDaoFactory().getAtlasRepository().findAll();
		Page<Atlas> atlases = getDaoFactory().getAtlasRepository().findAll( PageRequest.of( page, size ) );

		atlases.forEach( atlas -> System.out.println( atlas.toString() ) );

		return atlases;
	}

	@Override
	public Page <Atlas> searchAtlas( int page, int size, String keyword ){
		Page<Atlas> atlases =
				getDaoFactory().getAtlasRepository().searchAtlas( "%"+keyword.toLowerCase()+"%",PageRequest.of( page, size ) );
		System.out.println( keyword.toLowerCase() );
		return atlases;
	}
}
