package org.thibaut.wheretoclimb.webapp.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.Element;

import javax.persistence.*;
import java.util.List;

/**
 * Bean used to define an area with one or many climbing crag
 */
@Getter
@Setter
@NoArgsConstructor
public class AreaForm extends ElementForm{

//----------ATTRIBUTES----------

	private Integer id;
	private Atlas atlas;
	private String name;
	private int approachDuration;
	private String nearestCity;
	private String access;
	private String rockType;

	private double gpsLatitude;
	private double gpsLongitude;
	private int atltitude;
	private double parkingGpsLatitude;
	private double parkingGpsLongitude;
	private String parkingAccess;

}
