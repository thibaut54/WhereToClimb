package org.thibaut.wheretoclimb.model.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Bean used to define a spot, a piece of rock/cliff,
 * with one or many routes
 */
@Entity
@Table(name = "crag")
@PrimaryKeyJoinColumn(name = "element_id")
public class Crag extends Element {

//----------ATTRIBUTES----------

	@ManyToMany(mappedBy = "crags")
	private Collection<Atlas> atlases;
	@OneToMany/*(mappedBy = "crag", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "crag_id")
	private Collection<Route> routes;
	@OneToMany/*(mappedBy = "crag", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "crag_id")
	private Collection<Parking> parkings;
	@OneToOne(mappedBy = "crag")
	private GpsCoordinates cragGps;
	private String mapUrl;
	private String rockType;
	//uploader le file sur le server, et stocker en bdd l'url


//----------CONSTRUCTORS----------

	public Crag( ) {
	}

	public Crag( Collection< Atlas > atlases, Collection< Route > routes, Collection<Parking> parkings,
	             GpsCoordinates cragGps, String mapUrl, String rockType ) {
		this.atlases = atlases;
		this.routes = routes;
		this.parkings = parkings;
		this.cragGps = cragGps;
		this.mapUrl = mapUrl;
		this.rockType = rockType;
	}


//----------GETTERS & SETTERS----------

	public Collection< Atlas > getAtlases( ) {
		return atlases;
	}

	public void setAtlases( Collection< Atlas > atlases ) {
		this.atlases = atlases;
	}

	public Collection< Route > getRoutes( ) {
		return routes;
	}

	public void setRoutes( Collection< Route > routes ) {
		this.routes = routes;
	}

	public Collection< Parking > getParkings( ) {
		return parkings;
	}

	public void setParkings( Collection< Parking > parkings ) {
		this.parkings = parkings;
	}

	public GpsCoordinates getCragGps( ) {
		return cragGps;
	}

	public void setCragGps( GpsCoordinates cragGps ) {
		this.cragGps = cragGps;
	}

	public String getMapUrl( ) {
		return mapUrl;
	}

	public void setMapUrl( String mapUrl ) {
		this.mapUrl = mapUrl;
	}

	public String getRockType( ) {
		return rockType;
	}

	public void setRockType( String rockType ) {
		this.rockType = rockType;
	}
}
