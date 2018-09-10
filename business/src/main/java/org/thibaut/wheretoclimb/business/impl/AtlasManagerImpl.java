package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.beans.Atlas;

import java.util.List;

@Component
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {

	@Override
//	@Autowired
	public List< Atlas > getAtlases( ) {

		System.out.println( "In ATLASMANAGER, getAtlas() method" );

		List<Atlas> atlases = getDaoFactory().getAtlasRepository().findAll();

		atlases.forEach( atlas -> System.out.println( atlas.toString() ) );

		return atlases;
	}
}
