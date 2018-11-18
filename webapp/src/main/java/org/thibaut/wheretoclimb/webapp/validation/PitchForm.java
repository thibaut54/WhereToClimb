package org.thibaut.wheretoclimb.webapp.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Element;

import javax.persistence.*;

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

}
