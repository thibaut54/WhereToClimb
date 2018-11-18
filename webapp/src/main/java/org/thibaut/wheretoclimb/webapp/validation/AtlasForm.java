package org.thibaut.wheretoclimb.webapp.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AtlasForm extends ElementForm{

//----------ATTRIBUTES----------


	private String country;

	private String region;

	private String department;

	private boolean available;

	private List< Area > areas;

	private List<BookingRequest> bookingRequests;


	public AtlasForm( Atlas atlas ) {
		setId( atlas.getId() );
		setName( atlas.getName() );
//		setComments( atlas.getComments() );
//		setCreateDate( atlas.getCreateDate() );
//		setUpdateDate( LocalDateTime.now() );
		this.country = atlas.getCountry();
		this.region = atlas.getRegion();
		this.department = atlas.getDepartment();
		this.available = atlas.isAvailable();
//		this.areas = atlas.getAreas();
//		this.bookingRequests = atlas.getBookingRequests();
	}
}
