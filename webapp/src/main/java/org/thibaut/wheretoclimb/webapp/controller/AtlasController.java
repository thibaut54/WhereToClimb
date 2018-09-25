package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.beans.Atlas;

import java.util.List;

@Controller
public class AtlasController {

	@Autowired
	private ManagerFactory managerFactory;


	@GetMapping("/atlas")
	public String atlas( Model model){

		List< Atlas > atlases = this.managerFactory.getAtlasManager().getAtlases();

		model.addAttribute( "atlases", atlases );

		return "atlasPage";
	}
}
