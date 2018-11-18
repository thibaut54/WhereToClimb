package org.thibaut.wheretoclimb.webapp.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Crag;
import org.thibaut.wheretoclimb.model.entity.Element;
import org.thibaut.wheretoclimb.model.entity.Pitch;

import javax.persistence.*;
import java.util.List;

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
