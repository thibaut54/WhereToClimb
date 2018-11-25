package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Crag;

@Getter
@Setter
@NoArgsConstructor
public class CragForm extends ElementForm{

//----------ATTRIBUTES----------

	private Area area;
	private String access;
	private int approachDuration;


	public CragForm( Crag crag ) {
		setId( crag.getId() );
		setName( crag.getName() );
		this.access = crag.getAccess();
		this.approachDuration = crag.getApproachDuration();
	}
}
