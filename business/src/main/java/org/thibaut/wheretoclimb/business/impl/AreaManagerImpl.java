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
import org.thibaut.wheretoclimb.model.entity.QArea;
import org.thibaut.wheretoclimb.model.entity.QAtlas;
//import org.thibaut.wheretoclimb.model.entity.QArea;
//import org.thibaut.wheretoclimb.model.entity.QAtlas;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AreaManagerImpl extends AbstractManager implements AreaManager {


//	@Override
//	public Page< Area > searchArea( int page, int size, String keyword ){
//		Page< Area > areas =
//				getDaoFactory().getAreaRepository().searchArea(
//						"%"+keyword.toLowerCase()+"%", PageRequest.of( page, size ) );
//		return areas;
//	}

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
	public Page< Area > searchAreaByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject, String name, String country, String region, String department, String city ){

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;

		JPAQueryFactory queryFactory = new JPAQueryFactory(getEm());

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if( ! name.equals( "" ) ){
			booleanBuilder.and( qArea.name.containsIgnoreCase(name) );
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

		List<Area> areas = queryFactory.from(qAtlas)
				                   .innerJoin(qAtlas.areas, qArea)
				                   .where(booleanBuilder)
				                   .select(qArea)
				                   .fetch();

		long total = ( long ) areas.size( );

		return new PageImpl(areas, PageRequest.of( page, size ), total );
	}


//	private List<Atlas> customSearch(String areaName, String country, String region, String department) {
//		final JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//		final QAtlas qAtlas = QAtlas.atlas;
//		final QArea qArea = QArea.area;
//		return queryFactory.selectFrom(qAtlas)
//				       .innerJoin(qAtlas.areas, qArea)
//				       .where(
//						       qArea.name.containsIgnoreCase(areaName),
//						       qAtlas.country.containsIgnoreCase(country),
//						       qAtlas.department.containsIgnoreCase(department),
//						       qAtlas.region.containsIgnoreCase(region)
//				       )
//				       .fetch();
//	}

//	@Override
//	public List<Area> customSearch( String name, String country, String region, String department ) {
//		final JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//		final QAtlas qAtlas = QAtlas.atlas;
//		final QArea qArea = QArea.area;
//		return queryFactory.selectFrom(qArea)
//				       .innerJoin(qAtlas)
//				       .where(
//						       qArea.name.containsIgnoreCase(name),
//						       qAtlas.country.containsIgnoreCase(country),
//						       qAtlas.department.containsIgnoreCase(department),
//						       qAtlas.region.containsIgnoreCase(region)
//				       )
//				       .fetch();
//	}

//	public List<Area> getAreas(String country) {
//		QAtlas qAtlas = QAtlas.atlas;
//		QArea qArea = QArea.area;
//
//		JPAQuery query = new JPAQuery( em );
//
//		List<Area> area = ( List< Area > ) query.from(qArea)
//				       .innerJoin(qAtlas)
//				       .where(qAtlas.country.containsIgnoreCase( country ));
//
//		// je sais pas comment convertir cette JPAQuery en predicate pour qu'elle puisse s'intégrer dans ma methode
//	}
}
