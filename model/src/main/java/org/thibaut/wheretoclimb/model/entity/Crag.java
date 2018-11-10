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

	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;

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
	private int approachDuration;
	private int parentCreateId;
	//uploader le file sur le server, et stocker en bdd l'url



}
