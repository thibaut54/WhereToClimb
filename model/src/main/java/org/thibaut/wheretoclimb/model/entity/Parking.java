package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "parking")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Parking {

//----------ATTRIBUTES----------

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne(mappedBy = "parking")
	@JoinColumn(name = "crag_id")
	private GpsCoordinates parkingGps;
	private String indication;


//----------CONSTRUCTORS----------


	public Parking( GpsCoordinates parkingGps, String indication ) {
		this.parkingGps = parkingGps;
		this.indication = indication;
	}


}
