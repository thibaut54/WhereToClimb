package org.thibaut.wheretoclimb.webapp.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thibaut.wheretoclimb.model.entity.Element;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CragForm extends ElementForm{

//----------ATTRIBUTES----------

	private String mapUrl;
	private String access;
	private int approachDuration;
	//uploader le file sur le server, et stocker en bdd l'url



}
