package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
@ToString
//@EqualsAndHashCode( callSuper = false )
public class Pitch extends Element {

//----------ATTRIBUTES----------

	private String grade;
	private int length;
	private int nbAnchor;
	private double distanceMeterBetweenAnchors;
	private String style;


/*//----------CONSTRUCTORS----------


	public Pitch( String name, LocalDateTime createDate, LocalDateTime updateDate,
	              ArrayList< Comment > comments, String grade,
	              int length, int nbAnchor, double distanceMeterBetweenAnchors,
	              String style ) {
		super( name, createDate, updateDate, comments );
		this.grade = grade;
		this.length = length;
		this.nbAnchor = nbAnchor;
		this.distanceMeterBetweenAnchors = distanceMeterBetweenAnchors;
		this.style = style;
	}*/


}
