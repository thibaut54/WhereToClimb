package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;

@Entity
@Table(name = "gpsCoordinates")
public class GpsCoordinates {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double altitude;
	private double latitude;
	private double longitude;
	@OneToOne
//	@PrimaryKeyJoinColumn
//	@JoinColumn(name = "cragId")
	private Crag crag;
	@OneToOne
//	@PrimaryKeyJoinColumn
//	@JoinColumn(name = "parkingId")
	private Parking parking;


//----------CONSTRUCTORS----------

	public GpsCoordinates( ) {
	}

	public GpsCoordinates( double altitude, double latitude, double longitude,
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

	public double getAltitude( ) {
		return altitude;
	}

	public void setAltitude( double altitude ) {
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
