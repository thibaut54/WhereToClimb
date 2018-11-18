package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Bean used to define a precise itinerary that the climber must follow
 * if he wants to achieve the route,
 * beginning and ending at precise points
 */
@Entity
@Table(name = "route")
@PrimaryKeyJoinColumn(name = "element_id")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Route extends Element {

//----------ATTRIBUTES----------

	@ManyToOne
	@JoinColumn(name = "crag_id")
	private Crag crag;

	@OneToMany/*(mappedBy = "route", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "route_id")
	private List< Pitch > pitches;

	private String grade;
	private int length;
	private int nbAnchor;
	private boolean multiPitch;
	private String verticality;
	private String style;
	private int parentCreateId;

}
