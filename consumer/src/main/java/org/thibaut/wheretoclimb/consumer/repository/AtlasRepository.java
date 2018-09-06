package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.beans.Atlas;

@Repository
public interface AtlasRepository  extends JpaRepository< Atlas, Integer > {

}
