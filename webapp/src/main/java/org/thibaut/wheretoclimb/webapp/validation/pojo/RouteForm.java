package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Crag;
import org.thibaut.wheretoclimb.model.entity.Route;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RouteForm extends ElementForm{

//----------ATTRIBUTES----------


	private String grade;
	private int length;
	private int nbAnchor;
	private boolean multiPitch;
	private String verticality;
	private String style;
	private Crag crag;

	public RouteForm( Route route ) {
		setId( route.getId() );
		setName( route.getName() );
		this.grade = route.getGrade();
		this.nbAnchor = route.getNbAnchor();
		this.crag=  route.getCrag();
		this.verticality = route.getVerticality();
		this.style = route.getStyle();
		this.multiPitch = route.isMultiPitch();
	}
}
