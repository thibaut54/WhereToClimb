package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository< Area, Integer > {

	@Query("SELECT area FROM Area area WHERE LOWER(area.name) LIKE :keyword")
	public Page< Area > searchArea( @Param( "keyword" ) String keyword, Pageable pageable );

	public Area findByName(String name);

}
