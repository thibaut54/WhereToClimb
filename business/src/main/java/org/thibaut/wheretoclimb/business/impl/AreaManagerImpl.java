package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AreaManagerImpl extends AbstractManager implements AreaManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page< Area > searchArea( int page, int size, String keyword ){
		Page< Area > areas =
				getDaoFactory().getAreaRepository().searchArea(
						"%"+keyword.toLowerCase()+"%", PageRequest.of( page, size ) );
		return areas;
	}

	@Override
	public Page< Area > searchAreaByNameAndCountryAndRegionAndDepartment( int page, int size, String name, String country, String region, String department ){

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;

		ArrayList< Predicate > predicates = new ArrayList<>();

		List<Area> areas;

		if( !name.equals( "" ) ){
			predicates.add( qArea.name.containsIgnoreCase(name) );
		}
		if( !country.equals( "" ) ){
			predicates.add( ??? );
		}
		if( !region.equals( "" ) ){
//			predicates.add( ??? );
		}
		if( !department.equals( "" ) ){
//			predicates.add( ??? );
		}

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		for ( Predicate predicate: predicates ) {
			booleanBuilder.and(predicate);
		}

		areas = ( List< Area> ) getDaoFactory().getAreaRepository().findAll( booleanBuilder );

		long total = ( long ) areas.size( );

		return new PageImpl(areas, PageRequest.of( page, size ), total );
	}

	public List<Area> getAreas(String country) {
		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;
		return new JPAQuery(em).from(qArea)
				       .innerJoin(qAtlas)
				       .where(qAtlas.country.containsIgnoreCase( country ))
				       .fetch();// je sais pas comment obtenir une list d'Atlas
	}
}
