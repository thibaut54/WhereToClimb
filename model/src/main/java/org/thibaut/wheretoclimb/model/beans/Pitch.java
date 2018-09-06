package org.thibaut.wheretoclimb.model.beans;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Used to define a part of a route, when this one is made up of
 * many belay
 */
@Entity
@Table(name = "pitch")
@PrimaryKeyJoinColumn(name = "element_id")
public class Pitch extends Element implements Serializable {

//----------ATTRIBUTES----------

	private String grade;
	private int length;
	private int nbAnchor;
	private double distanceMeterBetweenAnchors;
	private String style;


//----------CONSTRUCTORS----------

	public Pitch(  ){
	}

	public Pitch( String name, Date createDate, Date updateDate,
	              ArrayList< Comment > comments, String grade,
	              int length, int nbAnchor, double distanceMeterBetweenAnchors,
	              String style ) {
		super( name, createDate, updateDate, comments );
		this.grade = grade;
		this.length = length;
		this.nbAnchor = nbAnchor;
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

	public int getLength( ) {
		return length;
	}

	public void setLength( int length ) {
		this.length = length;
	}

	public int getNbAnchor( ) {
		return nbAnchor;
	}

	public void setNbAnchor( int nbAnchor ) {
		this.nbAnchor = nbAnchor;
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
