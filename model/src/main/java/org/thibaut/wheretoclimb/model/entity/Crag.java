package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Bean used to define a spot, a piece of rock/cliff,
 * with one or many routes
 */
@Entity
@Table(name = "crag")
@PrimaryKeyJoinColumn(name = "element_id")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode( callSuper = true )
public class Crag extends Element {

//----------ATTRIBUTES----------

//	@ManyToMany(mappedBy = "crags")
//	private List< Atlas > atlases;
	@OneToMany/*(mappedBy = "crag", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "crag_id")
	private List< Route > routes;
	@OneToMany/*(mappedBy = "crag", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "crag_id")
	private List< Parking > parkings;
	@OneToOne(mappedBy = "crag")
	private GpsCoordinates cragGps;
	private String mapUrl;
	private String access;
	private Integer approachDuration;
	//uploader le file sur le server, et stocker en bdd l'url


//----------CONSTRUCTORS----------


//	public Crag( List< Atlas > atlases, List< Route > routes, List< Parking > parkings,
//	             GpsCoordinates cragGps, String mapUrl ) {
//		this.atlases = atlases;
//		this.routes = routes;
//		this.parkings = parkings;
//		this.cragGps = cragGps;
//		this.mapUrl = mapUrl;
//	}


}
