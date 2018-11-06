package org.thibaut.wheretoclimb.business.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.CragManager;
import org.thibaut.wheretoclimb.model.entity.*;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CragManagerImpl extends AbstractManager implements CragManager {


	@Override
	public Crag findById( Integer id ){

		if(id != null){
			Optional< Crag > crag =  getDaoFactory().getCragRepository().findById( id );
			if(crag.isPresent()){
				return crag.get();
			}
		}
		return null;
	}

	@Override
	public Page< Crag > searchCragByNameAndCountryAndRegionAndDepartment( int page, int size, String typeObject , String name, String country, String region, String department, String city){

		QAtlas qAtlas = QAtlas.atlas;
		QArea qArea = QArea.area;
		QCrag qCrag = QCrag.crag;

		JPAQueryFactory queryFactory = new JPAQueryFactory( getEm() );

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if( ! name.equals( "" ) ){
			booleanBuilder.and( qCrag.name.containsIgnoreCase(name) );
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

		List<Crag> crags = queryFactory.from(qAtlas)
				                   .innerJoin(qAtlas.areas, qArea)
				                   .innerJoin(qArea.crags, qCrag)
				                   .where(booleanBuilder)
				                   .select(qCrag)
//				                   .offset( page*size )
//				                   .limit( size )
				                   .fetch();

//		JPAQuery< ? > query = queryFactory.from(qAtlas);
//
//		if(typeObject.equals( "Crag" )){
//			query.innerJoin(qAtlas.areas, qArea)
//					.innerJoin(qArea.crags, qCrag);
//		}
//		List<Crag> crags = query.where(booleanBuilder)
//				                   .select(qCrag)
//				                   .fetch();

		long total = ( long ) crags.size( );

		return new PageImpl(crags, PageRequest.of( page, size ), total );
	}
}
