package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.expression.ConcatExpression;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Component
@Slf4j
public class AreaManagerImpl extends AbstractManager implements AreaManager {

	@PersistenceContext
	private EntityManager em;

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

//	@Override
//	public Page< Area > getAreas( int page, int size, Atlas atlas ){
//		Page< Area > areas =
//				getDaoFactory().getAreaRepository().searchArea(
//						"%"+keyword.toLowerCase()+"%", PageRequest.of( page, size ) );
//		return areas;
//	}

	@Override
	public Page< Area > searchAreaByNameAndCountryAndRegionAndDepartment( int page, int size, String name, String country, String region, String department ){

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;

//		ArrayList< Predicate > predicates = new ArrayList<>();

		final JPAQueryFactory queryFactory = new JPAQueryFactory(em);


		List<Area> areas = queryFactory.selectFrom(qArea)
				                   .innerJoin(qAtlas.areas, qArea)
				                   .where(
						                   qArea.name.containsIgnoreCase(name),
						                   qAtlas.country.containsIgnoreCase(country),
						                   qAtlas.department.containsIgnoreCase(department),
						                   qAtlas.region.containsIgnoreCase(region)
				                   )
				                   .fetch();

//		JPAQuery query = new JPAQuery( em );
//
//
//		if( !name.equals( "" ) ){
//			areas = ( List< Area > ) query.from(qArea).where( qArea.name.containsIgnoreCase(name) );
////			predicates.add( qArea.crags.containsIgnoreCase(name) );
//		}
//		if( !country.equals( "" ) ){
//			/*here should be the equivalent for
//			SELECT * FROM Area
//			INNER JOIN Atlas ON atlas.country =:country'
//			*/
//
//			areas = ( List< Area > ) query.from(qArea)
//					.innerJoin(qAtlas)
//					.where(qAtlas.country.containsIgnoreCase( country ));
////			predicates.add(  );
//		}
//		if( !region.equals( "" ) ){
////			predicates.add( ??? );
//		}
//		if( !department.equals( "" ) ){
////			predicates.add( ??? );
//		}
//
//		BooleanBuilder booleanBuilder = new BooleanBuilder();
//
//		for ( Predicate predicate: predicates ) {
//			booleanBuilder.and(predicate);
//		}

//		areas = ( List< Area> ) getDaoFactory().getAreaRepository().findAll( booleanBuilder );

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
