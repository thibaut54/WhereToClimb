package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

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
//@EqualsAndHashCode( callSuper = true )
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
	private Integer approachDuration;
	//uploader le file sur le server, et stocker en bdd l'url


//----------CONSTRUCTORS----------


	public Crag( Collection< Atlas > atlases, Collection< Route > routes, Collection<Parking> parkings,
	             GpsCoordinates cragGps, String mapUrl ) {
		this.atlases = atlases;
		this.routes = routes;
		this.parkings = parkings;
		this.cragGps = cragGps;
		this.mapUrl = mapUrl;
	}


}
