package org.thibaut.wheretoclimb.model.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gps_coordinates")
public class GpsCoordinates implements Serializable {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double latitude;
	private double longitude;
	private int altitude;
	@OneToOne
	private Crag crag;
	@OneToOne
	private Parking parking;


//----------CONSTRUCTORS----------

	public GpsCoordinates( ) {
	}

	public GpsCoordinates( int altitude, double latitude, double longitude,
	                       Crag crag, Parking parking ) {
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
		this.crag = crag;
		this.parking = parking;
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public int getAltitude( ) {
		return altitude;
	}

	public void setAltitude( int altitude ) {
		this.altitude = altitude;
	}

	public double getLatitude( ) {
		return latitude;
	}

	public void setLatitude( double latitude ) {
		this.latitude = latitude;
	}

	public double getLongitude( ) {
		return longitude;
	}

	public void setLongitude( double longitude ) {
		this.longitude = longitude;
	}

	public Crag getCrag( ) {
		return crag;
	}

	public void setCrag( Crag crag ) {
		this.crag = crag;
	}

	public Parking getParking( ) {
		return parking;
	}

	public void setParking( Parking parking ) {
		this.parking = parking;
	}
}
