package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Used to define a part of a route, when this one is made up of
 * many belay
 */
@Entity
@Table(name = "pitch")
@PrimaryKeyJoinColumn(name = "element_id")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Pitch extends Element {

//----------ATTRIBUTES----------

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;

	private String grade;
	private int length;
	private int nbAnchor;
	private String verticality;
	private String style;


}
