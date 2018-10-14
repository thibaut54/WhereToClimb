package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
@ToString
@EqualsAndHashCode
public class Route extends Element {

//----------ATTRIBUTES----------

	@OneToMany/*(mappedBy = "route", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "route_id")
	private Collection< Pitch > pitchList;
	private String grade;
	private int length;
	private int nbAnchor;
	private boolean multiPitch;


/*//----------CONSTRUCTORS----------


	public Route( String name, LocalDateTime createDate, LocalDateTime updateDate,
	              ArrayList< Comment > comments, Collection< Pitch > pitchList,
	              String grade, int length, int nbAnchor, boolean multiPitch ) {
		super( name, createDate, updateDate, comments );
		this.pitchList = pitchList;
		this.grade = grade;
		this.length = length;
		this.nbAnchor = nbAnchor;
		this.multiPitch = multiPitch;
	}*/

}
