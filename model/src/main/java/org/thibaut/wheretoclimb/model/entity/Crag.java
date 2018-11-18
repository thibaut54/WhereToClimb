package org.thibaut.wheretoclimb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@EqualsAndHashCode( callSuper = true )
public class Crag extends Element {

//----------ATTRIBUTES----------

	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;

	@OneToMany(mappedBy = "crag")
	private List< Route > routes;

	private String mapUrl;
	private String access;
	private int approachDuration;
	//uploader le file sur le server, et stocker en bdd l'url



}
