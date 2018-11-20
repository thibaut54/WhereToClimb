package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;

/**
 * Bean used to define an area with one or many climbing crag
 */
@Getter
@Setter
@NoArgsConstructor
public class AreaForm extends ElementForm{

//----------ATTRIBUTES----------

	private Atlas atlas;
	private int approachDuration;
	private String nearestCity;
	private String access;
	private String rockType;

	private double gpsLatitude;
	private double gpsLongitude;
	private int altitude;
	private String parkingAccess;


	public AreaForm ( Area area ){
		setId( area.getId( ) );
		setName( area.getName() );
		this.approachDuration = area.getApproachDuration( );
		this.nearestCity = area.getNearestCity();
		this.access = area.getAccess();
		this.rockType = area.getRockType();
		this.gpsLatitude = area.getGpsLatitude();
		this.gpsLongitude = area.getGpsLongitude();
		this.altitude = area.getAltitude();
		this.parkingAccess = area.getParkingAccess();
	}
}
