package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.entity.*;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.repository.query.Param;

@Component
@Slf4j
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {


	@Override
	public Page< Atlas > getAtlases( int page , int size ) {

		return getDaoFactory().getAtlasRepository().findAll( PageRequest.of( page, size ) );
	}


	@Override
	public Page< Atlas > searchAtlas( int page, int size, String keyword ){

/*		QAtlas qAtlas = QAtlas.atlas;

		List<Atlas> atlases = ( List< Atlas > ) getDaoFactory().getAtlasRepository().findAll( qAtlas.name.containsIgnoreCase( keyword ));

		long total = ( long ) atlases.size( );

		return new PageImpl(atlases, PageRequest.of( page, size ), page );*/

		return getDaoFactory().getAtlasRepository().searchAtlas("%"+keyword.toLowerCase()+"%", PageRequest.of( page, size ) );
	}


	@Override
	public Page<Atlas> searchAtlasByNameAndCountryAndRegionAndDepartment(int page, int size, String typeObject, String name, String country, String region, String department, String city){

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;

		JPAQueryFactory queryFactory = new JPAQueryFactory( getEm() );

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if( ! name.equals( "" ) ){
			booleanBuilder.and( qAtlas.name.containsIgnoreCase(name) );
		}
		if( ! country.equals( "" ) ){
			booleanBuilder.and( qAtlas.country.containsIgnoreCase(country) );
		}
		if( ! region.equals( "" ) ){
			booleanBuilder.and( qAtlas.region.containsIgnoreCase(region) );
		}
		if( ! department.equals( "" ) ){
			booleanBuilder.and( qAtlas.department.containsIgnoreCase(department) );
		}
		if( ! city.equals( "" ) ){
			booleanBuilder.and( qArea.nearestCity.containsIgnoreCase(city) );
		}

		List< Atlas > atlases = queryFactory.from(qAtlas)
				                        .innerJoin(qAtlas.areas, qArea)
				                        .where(booleanBuilder)
				                        .select(qAtlas)
				                        .fetch();

		long total = ( long ) atlases.size( );

		return new PageImpl(atlases, PageRequest.of( page, size ), total );
	}


	@Override
	public void deleteAtlas( Integer id ){
		getDaoFactory().getCommentRepository().deleteAll( findAtlasById( id ).getComments() );
		getDaoFactory().getAtlasRepository().deleteById( id );
	}


	@Override
	public void saveAtlas( Atlas atlas ){
//		getDaoFactory().getCommentRepository().saveAll( atlas.getComments() );
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
}
