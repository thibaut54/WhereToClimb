package org.thibaut.wheretoclimb.webapp.validation.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
