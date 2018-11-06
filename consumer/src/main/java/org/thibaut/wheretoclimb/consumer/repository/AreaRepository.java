package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository< Area, Integer > , QuerydslPredicateExecutor< Area > {

//	@Query("SELECT area FROM Area area WHERE LOWER(area.name) LIKE :keyword")
//	public Page< Area > searchArea( @Param( "keyword" ) String keyword, Pageable pageable );

	Area findByName( String name );

}
