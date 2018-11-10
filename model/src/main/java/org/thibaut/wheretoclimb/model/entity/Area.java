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
//@EqualsAndHashCode( callSuper = true )
public class Area extends Element {

//----------ATTRIBUTES----------

	@ManyToOne
	@JoinColumn(name = "atlas_id")
	private Atlas atlas;

	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private List< Crag > crags;

	@OneToMany/*(mappedBy = "area", fetch = FetchType.LAZY)*/
	@JoinColumn(name = "area_id")
	private List< Parking > parking;

	private int approachDuration;
	private String nearestCity;
	private String access;
	private String rockType;
	private int parentCreateId;


}
