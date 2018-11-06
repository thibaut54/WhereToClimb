package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.RouteManager;
import org.thibaut.wheretoclimb.model.entity.*;

import java.util.List;
import java.util.Optional;

@Component
public class RouteManagerImpl extends AbstractManager implements RouteManager {

	@Override
	public Route findById( Integer id ){

		if(id != null){
			Optional< Route > routeOpt =  getDaoFactory().getRouteRepository().findById( id );
			if(routeOpt.isPresent()){
				return routeOpt.get();
			}
		}
		return null;
	}

	@Override
	public Page< Route > searchRouteByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject, String name, String country, String region, String department, String city ){

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;
		QCrag qCrag = QCrag.crag;
		QRoute qRoute = QRoute.route;

		JPAQueryFactory queryFactory = new JPAQueryFactory( getEm() );

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if( ! name.equals( "" ) ){
			booleanBuilder.and( qRoute.name.containsIgnoreCase(name) );
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

		List<Route> routes = queryFactory.from(qAtlas)
				                   .innerJoin(qAtlas.areas, qArea)
				                   .innerJoin(qArea.crags, qCrag)
				                   .innerJoin(qCrag.routes, qRoute)
				                   .where(booleanBuilder)
				                   .select(qRoute)
				                   .fetch();

		long total = ( long ) routes.size( );

		return new PageImpl(routes, PageRequest.of( page, size ), total );
	}
}
