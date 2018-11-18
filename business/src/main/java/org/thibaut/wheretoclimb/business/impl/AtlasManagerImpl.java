package org.thibaut.wheretoclimb.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

import java.util.Optional;


@Component
@Slf4j
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {


	@Override
	public Page< Atlas > getAtlases( int page , int size ) {

		return getDaoFactory().getAtlasRepository().findAll( PageRequest.of( page, size ) );
	}


	@Override
	public Page< Atlas > searchAtlas( int page, int size, String keyword ){

		return getDaoFactory().getAtlasRepository().searchAtlas("%"+keyword.toLowerCase()+"%", PageRequest.of( page, size ) );
	}


	@Override
	public void deleteAtlas( Integer id ){
		getDaoFactory().getCommentRepository().deleteAll( findAtlasById( id ).getComments() );
		getDaoFactory().getAtlasRepository().deleteById( id );
	}


	@Override
	public void saveAtlas( Atlas atlas ){
		getDaoFactory().getAtlasRepository().save( atlas );
	}


	@Override
	public  Atlas findAtlasById( Integer id ){

		if(id != null){
			Optional< Atlas > atlas =  getDaoFactory().getAtlasRepository().findById( id );
			if(atlas.isPresent()){
				return atlas.get();
			}
		}
		return null;
	}


	@Override
	public  Atlas findAtlasByName( String name ){
		if(name != null){
			return getDaoFactory().getAtlasRepository().findByName( name );
		} else {
			return null;
		}

	}


	@Override
	public void saveBookingRequest( Integer atlasId, BookingRequest bookingRequest ) {
		log.info( "Trying to insert booking request for atlasID: " + atlasId );
		Atlas atlas = findAtlasById( atlasId );
		log.info( "Atlas: " + atlas );
		log.info( "Size booking request before: " + atlas.getBookingRequests().size() );
		atlas.getBookingRequests().add( bookingRequest );
		log.info( "Size booking request afer: " + atlas.getBookingRequests().size() );
		saveAtlas( atlas );
	}

	@Override
	public Atlas createAtlas( Atlas atlas ) {
		getDaoFactory().getAtlasRepository().save( atlas );
		return atlas;
	}
}
