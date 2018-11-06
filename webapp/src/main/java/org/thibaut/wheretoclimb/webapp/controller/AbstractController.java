package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.*;

import javax.persistence.EntityManager;
import java.util.Optional;

@Controller
public abstract class AbstractController {

	private ManagerFactory managerFactory;

	@Autowired
	private EntityManager em;


	public ManagerFactory getManagerFactory( ) {
		return managerFactory;
	}

	@Autowired
	public void setManagerFactory( ManagerFactory managerFactory ) {
		this.managerFactory = managerFactory;
	}


	protected void isUserAdmin( Model model ){
		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		boolean isConnected = false;

		//If there is a connected user, put a boolean at true in the model
		if( userConnectedOpt.isPresent() ){

			User userConnected = userConnectedOpt.get();

			isConnected = true;
			final boolean[] isAdmin = { false };

			userConnected.getRoles().forEach( role -> isAdmin[0] = role.getRole( ).equals( "ROLE_ADMIN" ) );

			model.addAttribute( "userIsAdmin" , isAdmin[0] );
			model.addAttribute( "isConnected", isConnected );

		}
	}

	protected void isCommented( Model model, Element atlas ){

		boolean elementIsCommented = false;

//		System.out.println( atlas.getComments().get( 0 ).getTitle());

		if( ! atlas.getComments().isEmpty() ){
			elementIsCommented = true;
			model.addAttribute( "atlasIsCommented", elementIsCommented );
		}

		System.out.println( "The atlas " + atlas.getName() + " has been commented: " + elementIsCommented );

	}

//	public Page< ? > searchElementByNameAndCountryAndRegionAndDepartmentAndCity( int page, int size, String name, String country, String region, String department, String city, String objectType ){
//
//		QAtlas qAtlas = QAtlas.atlas;
//		QArea qArea = QArea.area;
//		QCrag qCrag = QCrag.crag;;
//		QRoute qRoute = QRoute.route;
//		BooleanBuilder booleanBuilder = new BooleanBuilder();
//
//		JPAQueryFactory queryFactory = new JPAQueryFactory( em );
//
//		JPAQuery< ? > query = queryFactory.from(qAtlas)
//				                      .innerJoin(qAtlas.areas, qArea);
//
//		List< ? > result = null;
//
//		if(objectType.equals( "Atlas" )){
//			booleanBuilder.and( qAtlas.name.containsIgnoreCase(name) );
//		}
//		if(objectType.equals( "Area" )){
//			booleanBuilder.and( qArea.name.containsIgnoreCase(name) );
//		}
//		if(objectType.equals( "Crag" )) {
//			booleanBuilder.and( qCrag.name.containsIgnoreCase(name) );
//		}
//		if(objectType.equals( "Route" )) {
//			booleanBuilder.and( qRoute.name.containsIgnoreCase(name) );
//		}
//
//		if( ! country.equals( "" ) ){
//			booleanBuilder.and( qAtlas.country.containsIgnoreCase(country) );
//		}
//		if( ! region.equals( "" ) ){
//			booleanBuilder.and( qAtlas.region.containsIgnoreCase(region) );
//		}
//		if( ! department.equals( "" ) ){
//			booleanBuilder.and( qAtlas.department.containsIgnoreCase(department) );
//		}
//		if( ! city.equals( "" ) ){
//			booleanBuilder.and( qArea.nearestCity.containsIgnoreCase(city) );
//		}
//
//
//		if(objectType.equals( "Atlas" )){
//			result = (List<Atlas>)query.where(booleanBuilder)
//					                      .select(qAtlas)
//					                      .fetch();
//		}
//		else if (objectType.equals( "Area" )){
//			result = (List< Area >) query.where(booleanBuilder)
//					                        .select(qArea)
//					                        .fetch();
//		}
//		else if (objectType.equals( "Crag" )){
//			result = (List< Crag >) query.innerJoin(qArea.crags, qCrag)
//					                      .where(booleanBuilder)
//					                      .select(qCrag)
//					                      .fetch();
//		}
//		else if (objectType.equals( "Crag" )){
//			result = (List<Route>) query.innerJoin(qArea.crags, qCrag)
//					                       .innerJoin( qCrag.routes, qRoute  )
//					                       .where(booleanBuilder)
//					                       .select(qRoute)
//					                       .fetch();
//		}
//
//		return new PageImpl(result, PageRequest.of( page, size ), (long)result.size() );
//	}

}
