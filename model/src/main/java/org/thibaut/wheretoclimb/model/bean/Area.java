package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Bean used to define an area with one or many climbing crag
 */
@Entity
@Table(name="area")
@PrimaryKeyJoinColumn(name = "elementId")
public class Area extends Element{

//----------ATTRIBUTES----------

	@ManyToMany(mappedBy = "areas")
	private Collection< Atlas > atlases;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "areaId")
	private Collection< Crag > crags;
	private int approachDuration;
	private String locality;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "areaId")
	private Collection< Parking > parkings;

	//checker les sites existants pour voir comment ils ont divisé les topo..
	//Division pas claire chez l'existant


//----------CONSTRUCTORS----------

	public Area( ) {
	}

	public Area( ArrayList< Atlas > atlases, ArrayList< Crag > crags,
	             int approachDuration,
	             String locality, ArrayList< Parking > parkings ) {
		this.atlases = atlases;
		this.crags = crags;
		this.approachDuration = approachDuration;
		this.locality = locality;
		this.parkings = parkings;
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

	public Collection< Parking > getParkings( ) {
		return parkings;
	}

	public void setParkings( ArrayList< Parking > parkings ) {
		this.parkings = parkings;
	}
}
