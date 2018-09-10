package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.beans.Atlas;

import java.util.ArrayList;

@RestController
public class AtlasController {

	private ManagerFactory managerFactory;

	public AtlasController( ManagerFactory managerFactory ) {
		this.managerFactory = managerFactory;
	}

	@GetMapping("/atlases")
	public ArrayList< Atlas > getAtlases(){
		return ( ArrayList< Atlas > ) this.managerFactory.getAtlasManager().getAtlases();
	}
}
