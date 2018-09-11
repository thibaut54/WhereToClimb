//package org.thibaut.wheretoclimb.webapp.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
//import org.thibaut.wheretoclimb.model.beans.Atlas;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class AtlasController {
//
//	@Autowired
//	private ManagerFactory managerFactory;
//
////	public AtlasController( ManagerFactory managerFactory ) {
////		this.managerFactory = managerFactory;
////	}
//
//	@RequestMapping(value="/helloJsp")
//	public String index( /*Model model*/ ){
////		List<Atlas> atlases = ( ArrayList< Atlas > ) this.managerFactory.getAtlasManager().getAtlases();
////		model.addAttribute( "atlasList" , atlases );
//		return "hellotest";
//	}
//
////	@GetMapping("/atlases")
////	public ArrayList< Atlas > getAtlases(){
////		return ( ArrayList< Atlas > ) this.managerFactory.getAtlasManager().getAtlases();
////	}
//}
