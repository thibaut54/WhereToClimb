package org.thibaut.wheretoclimb.model.bean;

import javax.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(mappedBy = "parking")
	@JoinColumn(name = "cragId")
	private GpsCoordinates parkingGps;
	private String indication;


//----------CONSTRUCTORS----------

	public Parking( ) {
	}

	public Parking( GpsCoordinates parkingGps, String indication ) {
		this.parkingGps = parkingGps;
		this.indication = indication;
	}


//----------GETTERS & SETTERS----------

	public Integer getId( ) {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public GpsCoordinates getParkingGps( ) {
		return parkingGps;
	}

	public void setParkingGps( GpsCoordinates parkingGps ) {
		this.parkingGps = parkingGps;
	}

	public String getIndication( ) {
		return indication;
	}

	public void setIndication( String indication ) {
		this.indication = indication;
	}
}
