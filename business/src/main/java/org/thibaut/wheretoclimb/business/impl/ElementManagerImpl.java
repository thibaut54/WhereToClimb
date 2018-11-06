package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.ElementManager;
import org.thibaut.wheretoclimb.model.entity.*;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class ElementManagerImpl extends AbstractManager implements ElementManager {

	@Override
	public Page searchElementByNameAndCountryAndRegionAndDepartmentAndCity( Pageable pageable, String objectType, String name, String country, String region, String department, String city  ){

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;
		QCrag qCrag = QCrag.crag;
		QRoute qRoute = QRoute.route;

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		JPAQueryFactory queryFactory = new JPAQueryFactory( getEm() );


		List< ? > result = null;

		if( ! name.equals( "" )){

			if(objectType.equals( "Atlas" ) ){
				booleanBuilder.and( qAtlas.name.containsIgnoreCase(name) );
			}
			if(objectType.equals( "Area" )){
				booleanBuilder.and( qArea.name.containsIgnoreCase(name) );
			}
			if(objectType.equals( "Crag" )) {
				booleanBuilder.and( qCrag.name.containsIgnoreCase(name) );
			}
			if(objectType.equals( "Route" )) {
				booleanBuilder.and( qRoute.name.containsIgnoreCase(name) );
			}
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

		JPAQuery< ? > query = queryFactory.from(qAtlas);

		if(objectType.equals( "Atlas" )){
			if( ! city.equals( "" )){
				/*query = */query.innerJoin( qAtlas.areas, qArea );
			}
			result = query.where(booleanBuilder)
					                      .select(qAtlas)
					                      .fetch();
		}
		else if (objectType.equals( "Area" )){
			result = query.innerJoin(qAtlas.areas, qArea)
					                        .where(booleanBuilder)
					                        .select(qArea)
					                        .fetch();
		}
		else if (objectType.equals( "Crag" )){
			result = query.innerJoin(qAtlas.areas, qArea)
					                        .innerJoin(qArea.crags, qCrag)
					                        .where(booleanBuilder)
					                        .select(qCrag)
					                        .fetch();
		}
		else if (objectType.equals( "Route" )){
			result = query.innerJoin( qAtlas.areas, qArea )
				                         .innerJoin(qArea.crags, qCrag)
				                         .innerJoin( qCrag.routes, qRoute  )
				                         .where(booleanBuilder)
					                     .select(qRoute)
					                     .fetch();
		}

		List<?> list;

		if (result.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, result.size());
			list = result.subList(startItem, toIndex);
		}

		return new PageImpl(list, PageRequest.of( currentPage, pageSize ), (long)result.size() );
	}
}
