package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Crag;
import org.thibaut.wheretoclimb.model.entity.Pitch;
import org.thibaut.wheretoclimb.model.entity.Route;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PitchForm extends ElementForm{

//----------ATTRIBUTES----------


	private String grade;
	private int length;
	private int nbAnchor;
	private String verticality;
	private String style;
	private Route route;

	public PitchForm( Pitch pitch ) {
		setId( pitch.getId() );
		setName( pitch.getName() );
		this.grade = pitch.getGrade();
		this.nbAnchor = pitch.getNbAnchor();
		this.route=  pitch.getRoute();
		this.verticality = pitch.getVerticality();
		this.style = pitch.getStyle();
	}
}
