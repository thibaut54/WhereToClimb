package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Bean used to define an area with one or many climbing crag
 */
@Entity
@Table(name="area")
@PrimaryKeyJoinColumn(name = "elementId")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode( callSuper = true )
public class Area extends Element {

//----------ATTRIBUTES----------

	@ManyToMany(mappedBy = "areas")
	private Collection< Atlas > atlases;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private Collection< Crag > crags;
	private int approachDuration;
	private String locality;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private Collection< Parking > parking;
	private String rockType;


	//checker les sites existants pour voir comment ils ont divisé les topo..
	//Division pas claire chez l'existant


//----------CONSTRUCTORS----------


	public Area( String name, LocalDateTime createDate, LocalDateTime updateDate,
	             ArrayList< Comment > comments, Collection< Atlas > atlases,
	             Collection< Crag > crags, int approachDuration, String locality,
	             Collection< Parking > parking ) {
		super( name, createDate, updateDate, comments );
		this.atlases = atlases;
		this.crags = crags;
		this.approachDuration = approachDuration;
		this.locality = locality;
		this.parking = parking;
	}


}
