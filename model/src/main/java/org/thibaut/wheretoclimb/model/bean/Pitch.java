package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

/**
 * Used to define a part of a route, when this one is made up of
 * many belay
 */
@Entity
@Table(name = "pitch")
@PrimaryKeyJoinColumn(name = "elementId")
public class Pitch extends Element{

//----------ATTRIBUTES----------

	private String grade;
	private int nbAnchor;
	private int length;
	private double distanceMeterBetweenAnchors;
	private String style;
//	add attribut "engagement"


//----------CONSTRUCTORS----------

	public Pitch(  ){
	}

	public Pitch( String grade, int nbAnchor, int length, double distanceMeterBetweenAnchors, String style ) {
		this.grade = grade;
		this.nbAnchor = nbAnchor;
		this.length = length;
		this.distanceMeterBetweenAnchors = distanceMeterBetweenAnchors;
		this.style = style;
	}


//----------GETTERS & SETTERS----------

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

	public double getDistanceMeterBetweenAnchors( ) {
		return distanceMeterBetweenAnchors;
	}

	public void setDistanceMeterBetweenAnchors( double distanceMeterBetweenAnchors ) {
		this.distanceMeterBetweenAnchors = distanceMeterBetweenAnchors;
	}

	public String getStyle( ) {
		return style;
	}

	public void setStyle( String style ) {
		this.style = style;
	}
}
