package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Bean used to define a precise itinerary that the climber must follow
 * if he wants to achieve the route,
 * beginning and ending at precise points
 */
@Entity
@Table(name = "route")
@PrimaryKeyJoinColumn(name = "elementId")
public class Route extends Element{

//----------ATTRIBUTES----------

	@OneToMany/*(mappedBy = "route", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "routeId")
	private Collection< Pitch > pitchList;
	private String grade;
	private int nbAnchor;
	private int length;


//----------CONSTRUCTORS----------

	public Route( ) {
	}

	public Route( Collection< Pitch > pitchList, String grade, int nbAnchor, int length ) {
		this.pitchList = pitchList;
		this.grade = grade;
		this.nbAnchor = nbAnchor;
		this.length = length;
	}


//----------GETTERS & SETTERS----------

	public Collection< Pitch > getPitchList( ) {
		return pitchList;
	}

	public void setPitchList( Collection< Pitch > pitchList ) {
		this.pitchList = pitchList;
	}

	public String getGrade( ) {
		return grade;
	}

	public void setGrade( String grade ) {
		this.grade = grade;
	}

	public int getNbAnchor( ) {
		return nbAnchor;
	}

	public void setNbAnchor( int nbAnchor ) {
		this.nbAnchor = nbAnchor;
	}

	public int getLength( ) {
		return length;
	}

	public void setLength( int length ) {
		this.length = length;
	}
}
