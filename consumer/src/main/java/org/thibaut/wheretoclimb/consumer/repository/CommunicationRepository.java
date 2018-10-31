package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.Comment;
import org.thibaut.wheretoclimb.model.entity.Communication;

@Repository
public interface CommunicationRepository extends JpaRepository< Communication, Integer > {
}
