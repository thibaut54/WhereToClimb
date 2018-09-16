//package org.thibaut.wheretoclimb.webapp.controller.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
//import org.thibaut.wheretoclimb.model.beans.Atlas;
//
//import java.util.ArrayList;
//
//@RestController
//public class AtlasRestController {
//
//	private ManagerFactory managerFactory;
//
//	@Autowired
//	public AtlasRestController( ManagerFactory managerFactory ) {
//		this.managerFactory = managerFactory;
//	}
//
//
//	@GetMapping("/atlasRest")
//	public ArrayList< Atlas > getAtlases(){
//		return ( ArrayList< Atlas > ) this.managerFactory.getAtlasManager().getAtlases();
//	}
//}
