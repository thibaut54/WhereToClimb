package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thibaut.wheretoclimb.model.entity.Element;

@Repository
public interface ElementRepository extends JpaRepository< Element, Integer > {

	public Element findElementById(Integer elementId);
}
