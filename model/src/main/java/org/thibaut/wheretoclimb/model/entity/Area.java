package org.thibaut.wheretoclimb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Bean used to define an area with one or many climbing crag
 */
@Entity
@Table(name="area")
@PrimaryKeyJoinColumn(name = "element_id")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode( callSuper = true )
public class Area extends Element {

//----------ATTRIBUTES----------

	@ManyToMany(mappedBy = "areas")
	private List< Atlas > atlases;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private List< Crag > crags;
	private int approachDuration;
	private String locality;
	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private List< Parking > parking;
	private String rockType;



/*//----------CONSTRUCTORS----------


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
	}*/


}
