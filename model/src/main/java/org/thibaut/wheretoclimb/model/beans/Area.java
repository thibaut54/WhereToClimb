package org.thibaut.wheretoclimb.model.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Bean used to define an area with one or many climbing crag
 */
@Entity
@Table(name="area")
@PrimaryKeyJoinColumn(name = "elementId")
public class Area extends Element {

//----------ATTRIBUTES----------

	@ManyToMany(mappedBy = "areas")
	@JsonBackReference
	private Collection< Atlas > atlases;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private Collection< Crag > crags;
	private int approachDuration;
	private String locality;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private Collection< Parking > parking;

	//checker les sites existants pour voir comment ils ont divisé les topo..
	//Division pas claire chez l'existant


//----------CONSTRUCTORS----------

	public Area( ) {
	}

	public Area( String name, Date createDate, Date updateDate,
	             ArrayList< Comment > comments, Collection< Atlas > atlases,
	             Collection< Crag > crags, int approachDuration, String locality,
	             Collection< Parking > parking ) {
		super( name, createDate, updateDate, comments );
		this.atlases = atlases;
		this.crags = crags;
		this.approachDuration = approachDuration;
		this.locality = locality;
		this.parking = parking;
	}

	//----------GETTERS & SETTERS----------

	public Collection< Atlas > getAtlases( ) {
		return atlases;
	}

	public void setAtlases( ArrayList< Atlas > atlases ) {
		this.atlases = atlases;
	}

	public Collection< Crag > getCrags( ) {
		return crags;
	}

	public void setCrags( ArrayList< Crag > crags ) {
		this.crags = crags;
	}

	public int getApproachDuration( ) {
		return approachDuration;
	}

	public void setApproachDuration( int approachDuration ) {
		this.approachDuration = approachDuration;
	}

	public String getLocality( ) {
		return locality;
	}

	public void setLocality( String locality ) {
		this.locality = locality;
	}

	public Collection< Parking > getParking( ) {
		return parking;
	}

	public void setParking( Collection< Parking > parking ) {
		this.parking = parking;
	}
}
