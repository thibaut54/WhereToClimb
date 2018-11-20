package org.thibaut.wheretoclimb.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@OneToMany(mappedBy = "area")
	private List< Crag > crags;

	private int approachDuration;
	private String nearestCity;
	private String access;
	private String rockType;

	private double gpsLatitude;
	private double gpsLongitude;
	private int altitude;
	private String parkingAccess;

}
