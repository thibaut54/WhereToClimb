package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private int parentCreateId;

}
