package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gps_coordinates")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GpsCoordinates {

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


	public GpsCoordinates( int altitude, double latitude, double longitude,
	                       Crag crag, Parking parking ) {
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
		this.crag = crag;
		this.parking = parking;
	}

}
