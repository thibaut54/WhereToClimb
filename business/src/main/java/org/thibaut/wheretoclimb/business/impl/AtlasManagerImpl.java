package org.thibaut.wheretoclimb.business.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;

import java.util.Optional;

//import org.springframework.data.repository.query.Param;

@Component
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {

	@Override
	public Page< Atlas > getAtlases( int page , int size ) {

		Page<Atlas> atlases = getDaoFactory().getAtlasRepository().findAll( PageRequest.of( page, size ) );

		return atlases;
	}


	@Override
	public Page< Atlas > searchAtlas( int page, int size, String keyword ){
		Page< Atlas > atlases =
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


	@Override
	public  Atlas findById( Integer id ){

		if(id != null){
			Optional< Atlas > atlas =  getDaoFactory().getAtlasRepository().findById( id );
			if(atlas.isPresent()){
				return atlas.get();
			}
		}
		return null;
	}

	@Override
	public  Atlas findByName( String name ){
		if(name != null){
			return getDaoFactory().getAtlasRepository().findByName( name );
		} else {
			return null;
		}

	}
}
