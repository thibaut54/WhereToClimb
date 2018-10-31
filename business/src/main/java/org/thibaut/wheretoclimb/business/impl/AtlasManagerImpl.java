package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.QArea;
import org.thibaut.wheretoclimb.model.entity.QAtlas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.types.dsl.Expressions.stringPath;

//import org.springframework.data.repository.query.Param;

@Component
@Slf4j
public class AtlasManagerImpl extends AbstractManager implements AtlasManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page< Atlas > getAtlases( int page , int size ) {

		Page<Atlas> atlases = getDaoFactory().getAtlasRepository().findAll( PageRequest.of( page, size ) );

		return atlases;
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
	public Page<Atlas> searchAtlasByNameAndCountryAndRegionAndDepartment( int page, int size, String name, String country, String region, String department ){

		QAtlas qAtlas = QAtlas.atlas;

		ArrayList< Predicate > predicates = new ArrayList<>();

//		List<Atlas> atlases = customSearch( name, country, region, department );
		List<Atlas> atlases;

		if( !name.equals( "" ) ){
			predicates.add( qAtlas.name.containsIgnoreCase(name) );
		}
		if( !country.equals( "" ) ){
			predicates.add( qAtlas.country.containsIgnoreCase(country) );
		}
		if( !region.equals( "" ) ){
			predicates.add( qAtlas.region.containsIgnoreCase(region) );
		}
		if( !department.equals( "" ) ){
			predicates.add( qAtlas.department.containsIgnoreCase(department) );
		}

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		for ( Predicate predicate: predicates ) {
			booleanBuilder.and(predicate);
		}

		atlases = ( List< Atlas > ) getDaoFactory().getAtlasRepository().findAll( booleanBuilder );

		long total = ( long ) atlases.size( );

		return new PageImpl(atlases, PageRequest.of( page, size ), total );
	}

//	@Override
	public List<Atlas> customSearch( String name, String country, String region, String department ) {
		final JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		final QAtlas qAtlas = QAtlas.atlas;
		return queryFactory.selectFrom(qAtlas)
				       .where(
						       qAtlas.name.containsIgnoreCase(name),
						       qAtlas.country.containsIgnoreCase(country),
						       qAtlas.department.containsIgnoreCase(department),
						       qAtlas.region.containsIgnoreCase(region)
				       )
				       .fetch();
	}

//	@Override
//	public Page<Atlas> searchAtlasByNameAndCountryAndRegionAndDepartment( int page, int size, String name, String country, String region, String department ){
//
//		QAtlas qAtlas = QAtlas.atlas;
//
//		List<Atlas> atlases = ( List< Atlas > ) getDaoFactory().getAtlasRepository().findAll(
//				qAtlas.name.containsIgnoreCase( name )
//						.andAnyOf( qAtlas.country.containsIgnoreCase( country ) )
//						.andAnyOf( qAtlas.region.containsIgnoreCase( region ) )
//						.andAnyOf( qAtlas.department.containsIgnoreCase( department ) ) );
//
//		return new PageImpl(atlases, PageRequest.of( page, size ), atlases.size() );
//	}


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


	@Override
	public void saveBookingRequest( Integer atlasId, BookingRequest bookingRequest ) {
		log.info( "Trying to insert booking request for atlasID: " + atlasId );
		Atlas atlas = findById( atlasId );
		log.info( "Atlas: " + atlas );
		log.info( "Size booking request before: " + atlas.getBookingRequests().size() );
		atlas.getBookingRequests().add( bookingRequest );
		log.info( "Size booking request afer: " + atlas.getBookingRequests().size() );
		saveAtlas( atlas );
	}
}
