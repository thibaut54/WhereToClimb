package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.Pitch;

@Repository
public interface PitchRepository extends JpaRepository< Pitch, Integer > {
}
