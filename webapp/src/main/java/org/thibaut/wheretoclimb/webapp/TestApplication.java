//package org.thibaut.wheretoclimb.webapp;
//
//import groovy.util.logging.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.thibaut.wheretoclimb.business.contract.PasswordManager;
//import org.thibaut.wheretoclimb.consumer.repository.*;
//import org.thibaut.wheretoclimb.model.entity.*;
//import org.thibaut.wheretoclimb.util.GenericBuilder;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Slf4j
//public class TestApplication implements CommandLineRunner {
//
//	private PasswordManager passwordManager;
//	private UserRepository userRepository;
//	private AtlasRepository atlasRepository;
//	private RoleRepository roleRepository;
//	private AreaRepository areaRepository;
//	private CommentRepository commentRepository;
//	private BookingRequestRepository bookingRequestRepository;
//	private CragRepository cragRepository;
//	private RouteRepository routeRepository;
//	private PitchRepository pitchRepository;
//	private ElementRepository elementRepository;
//
//
//	public TestApplication( PasswordManager passwordManager, AtlasRepository atlasManager, UserRepository userRepository, AtlasRepository atlasRepository, RoleRepository roleRepository, AreaRepository areaRepository, CommentRepository commentRepository, BookingRequestRepository bookingRequestRepository, CragRepository cragRepository, RouteRepository routeRepository, PitchRepository pitchRepository, ElementRepository elementRepository ) {
//		this.passwordManager = passwordManager;
//		this.userRepository = userRepository;
//		this.atlasRepository = atlasRepository;
//		this.roleRepository = roleRepository;
//		this.areaRepository = areaRepository;
//		this.commentRepository = commentRepository;
//		this.bookingRequestRepository = bookingRequestRepository;
//		this.cragRepository = cragRepository;
//		this.routeRepository = routeRepository;
//		this.pitchRepository = pitchRepository;
//		this.elementRepository = elementRepository;
//	}
//
//	/**
//	 * Callback used to run the entity.
//	 *
//	 * @param args incoming main method arguments
//	 * @throws Exception on error
//	 */
//	@Override
//	public void run( String... args ) throws Exception {
//
//		System.out.println( "CONSUMER APP RUN" );
//
//		//-----CLEAN DB
////		this.commentRepository.deleteAll();
////		this.bookingRequestRepository.deleteAll();
////		this.pitchRepository.deleteAll();
////		this.routeRepository.deleteAll();
////		this.cragRepository.deleteAll();
////		this.areaRepository.deleteAll();
////		this.atlasRepository.deleteAll();
////		this.elementRepository.deleteAll();
////		this.userRepository.deleteAll();
////		this.roleRepository.deleteAll();
//
//
//		//-----POPULATE USER_ROLES
//
//		List< Role > roles = new ArrayList<>();
//
//		roles.add(new Role("ROLE_USER"));
//		roles.add(new Role("ROLE_ADMIN"));
//
//		this.roleRepository.saveAll( roles );
//
//
//
//		//-----POPULATE USERS
//
//		List< User> users = new ArrayList<>(  );
//
//		//pwd = 1235
//		users.add( GenericBuilder.of( User::new )
//							.with(User::setGender, "male")
//							.with(User::setFirstName, "John")
//							.with(User::setLastName, "Doe")
//							.with(User::setUserName, "TheJohn")
//							.with(User::setEmail, "john@gmail.com")
//							.with(User::setCreateAccountDate, LocalDateTime.now())
//							.with(User::setPassword, passwordManager.crypt( "1235"))
//							.build());
//
//		//pwd = 1265
//		users.add( GenericBuilder.of( User::new )
//				           .with(User::setGender, "female")
//				           .with(User::setFirstName, "Lea")
//				           .with(User::setLastName, "Corvoisier")
//				           .with(User::setUserName, "Lele")
//				           .with(User::setEmail, "lea@gmail.com")
//				           .with(User::setCreateAccountDate, LocalDateTime.now())
//				           .with(User::setPassword, passwordManager.crypt( "1265"))
//				           .build());
//
//		//pwd = 123
//		users.add( GenericBuilder.of( User::new )
//				           .with(User::setGender, "male")
//				           .with(User::setFirstName, "Admin")
//				           .with(User::setLastName, "Admin")
//				           .with(User::setUserName, "Admin")
//				           .with(User::setEmail, "admin@gmail.com")
//				           .with(User::setCreateAccountDate, LocalDateTime.now())
//				           .with(User::setPassword, passwordManager.crypt( "123"))
//				           .build());
//
//
//		//-----SET ROLE
//
//		List< Role > rolesUser1 = new ArrayList<>();
//		rolesUser1.add( this.roleRepository.findByRoleLike( "%USER" ) );
//
//		users.get( 0 ).setRoles( rolesUser1 );
//		users.get( 1 ).setRoles( rolesUser1 );
//
//		List< Role > rolesUser3 = new ArrayList<>();
//		rolesUser3.add( this.roleRepository.findByRoleLike( "%USER" ) );
//		rolesUser3.add( this.roleRepository.findByRoleLike( "%ADMIN" ) );
//		users.get( 2).setRoles( rolesUser3 );
//
//
//		//-----SAVE ALL USERS
//
//		this.userRepository.saveAll( users );
//
//
//		//-----POPULATE ATLAS
//
//		Atlas atlas1 = GenericBuilder.of( Atlas::new )
//								.with( Atlas::setName, "Grimper en Lorraine" )
//								.with( Atlas::setCreateDate, LocalDateTime.now() )
//								.with( Atlas::setAvailable, true)
//								.with( Atlas::setCountry, "France")
//								.with( Atlas::setRegion, "Lorraine")
//								.with( Atlas::setUser, users.get( 0 ))
//								.build();
//		Atlas atlas2 = GenericBuilder.of( Atlas::new )
//								.with( Atlas::setName, "Grimper en Rhône-Alpes" )
//								.with( Atlas::setCreateDate, LocalDateTime.now() )
//								.with( Atlas::setAvailable, true)
//								.with( Atlas::setCountry, "France")
//								.with( Atlas::setRegion, "Rhône-Alpes")
//								.with( Atlas::setUser, users.get( 0 ))
//								.build();
//		Atlas atlas3 = GenericBuilder.of( Atlas::new )
//								.with( Atlas::setName, "Climb in USA" )
//								.with( Atlas::setCreateDate, LocalDateTime.now() )
//								.with( Atlas::setAvailable, true)
//								.with( Atlas::setCountry, "USA")
//								.with( Atlas::setRegion, "Arizona")
//								.with( Atlas::setUser, users.get( 1 ))
//								.build();
//		Atlas atlas4 = GenericBuilder.of( Atlas::new )
//								.with( Atlas::setName, "Grimper en Ile de France" )
//								.with( Atlas::setCreateDate, LocalDateTime.now() )
//								.with( Atlas::setAvailable, false)
//								.with( Atlas::setCountry, "France")
//								.with( Atlas::setRegion, "IDF")
//								.with( Atlas::setUser, users.get( 1 ))
//								.build();
//
//
//
//		List< Atlas > atlases = new ArrayList<Atlas>();
//
//		atlases.add( atlas1 );
//		atlases.add( atlas2 );
//		atlases.add( atlas3 );
//		atlases.add( atlas4 );
//
//		this.atlasRepository.saveAll( atlases );
//
//
//		//-----POPULATE AREA
//
//		ArrayList< Area > areas = new ArrayList<Area>();
//		ArrayList< Area > areas2 = new ArrayList<Area>();
//
//		areas.add( GenericBuilder.of( Area::new )
//				           .with( Area::setName, "Maron" )
//				           .with( Area::setCreateDate, LocalDateTime.now() )
//				           .with( Area::setUpdateDate, LocalDateTime.now() )
//				           .with( Area::setApproachDuration, 12 )
//				           .with( Area::setNearestCity, "Maron")
//				           .with( Area::setAccess, "Au bord de la Moselle, 4 km en amont de la ville de Maron" )
//				           .with( Area::setAtlas, atlas1 )
//				           .build());
//		areas.add( GenericBuilder.of( Area::new )
//				           .with( Area::setName, "Liverdun" )
//				           .with( Area::setCreateDate, LocalDateTime.now() )
//				           .with( Area::setUpdateDate, LocalDateTime.now() )
//				           .with( Area::setApproachDuration, 5 )
//				           .with( Area::setNearestCity, "Liverdun")
//				           .with( Area::setAccess, "Vers Liverdun" )
//				           .with( Area::setAtlas, atlas1 )
//				           .build());
//		areas2.add( GenericBuilder.of( Area::new )
//				            .with( Area::setName, "Cul de chien" )
//				            .with( Area::setCreateDate, LocalDateTime.now() )
//				            .with( Area::setUpdateDate, LocalDateTime.now() )
//				            .with( Area::setApproachDuration, 12 )
//				            .with( Area::setNearestCity, "Fontainebleau")
//				            .with( Area::setAccess, "Au fond dans la forêt." )
//				            .with( Area::setAtlas, atlas2 )
//				            .build());
//		areas2.add( GenericBuilder.of( Area::new )
//				            .with( Area::setName, "AreaTest" )
//				            .with( Area::setCreateDate, LocalDateTime.now() )
//				            .with( Area::setUpdateDate, LocalDateTime.now() )
//				            .with( Area::setApproachDuration, 5 )
//				            .with( Area::setNearestCity, "One city")
//				            .with( Area::setAccess, "Access test." )
//				            .with( Area::setAtlas, atlas2 )
//				            .build());
//
//
//
//		this.areaRepository.saveAll( areas );
//		this.areaRepository.saveAll( areas2 );
//
//
//		//-----POPULATE CRAG
//
//		ArrayList< Crag > crags = new ArrayList<Crag>();
//		ArrayList< Crag > crags2 = new ArrayList<Crag>();
//
//		crags.add( GenericBuilder.of( Crag::new )
//				           .with( Crag::setName, "Secteur bas" )
//				           .with( Crag::setCreateDate, LocalDateTime.now() )
//				           .with( Crag::setUpdateDate, LocalDateTime.now() )
//				           .with( Crag::setApproachDuration, 12 )
//				           .with( Crag::setAccess, "Pour ce secteur, s'arrêter à la 1ère falaise." )
//				           .with( Crag::setArea, areas.get( 0 ) )
//				           .build());
//		crags.add( GenericBuilder.of( Crag::new )
//				           .with( Crag::setName, "Secteur haut" )
//				           .with( Crag::setCreateDate, LocalDateTime.now() )
//				           .with( Crag::setUpdateDate, LocalDateTime.now() )
//				           .with( Crag::setApproachDuration, 12 )
//				           .with( Crag::setAccess, "Pour ce secteur, à la 1ère falaise, continuer sur la gauche en montant jusqu'à la 2ème falaise." )
//				           .with( Crag::setArea, areas.get( 0 ) )
//				           .build());
//
//		crags2.add( GenericBuilder.of( Crag::new )
//				            .with( Crag::setName, "Secteur test 1" )
//				            .with( Crag::setCreateDate, LocalDateTime.now() )
//				            .with( Crag::setUpdateDate, LocalDateTime.now() )
//				            .with( Crag::setApproachDuration, 12 )
//				            .with( Crag::setAccess, "Pour ce secteur, s'arrêter à la 1ère falaise." )
//				            .with( Crag::setArea, areas.get( 1 ) )
//				            .build());
//		crags2.add( GenericBuilder.of( Crag::new )
//				            .with( Crag::setName, "Secteur test 2" )
//				            .with( Crag::setCreateDate, LocalDateTime.now() )
//				            .with( Crag::setUpdateDate, LocalDateTime.now() )
//				            .with( Crag::setApproachDuration, 12 )
//				            .with( Crag::setAccess, "Pour ce secteur, à la 1ère falaise, continuer sur la gauche en montant jusqu'à la 2ème falaise." )
//				            .with( Crag::setArea, areas2.get( 0 ) )
//				            .build());
//
//		this.cragRepository.saveAll( crags );
//		this.cragRepository.saveAll( crags2 );
//
//
//		//-----POPULATE ROUTE
//
//		ArrayList< Route > routes = new ArrayList<>();
//
//		routes.add( GenericBuilder.of(Route::new)
//				            .with( Route::setName, "La fissure" )
//				            .with( Route::setCreateDate, LocalDateTime.now())
//				            .with( Route::setGrade, "6a")
//				            .with( Route::setLength, 20)
//				            .with( Route::setNbAnchor, 9)
//				            .with( Route::setVerticality, "Léger dévers")
//				            .with( Route::setCrag, crags.get( 0 ))
//				            .build());
//
//		routes.add( GenericBuilder.of(Route::new)
//				            .with( Route::setName, "Le dièdre" )
//				            .with( Route::setCreateDate, LocalDateTime.now())
//				            .with( Route::setGrade, "6c")
//				            .with( Route::setLength, 20)
//				            .with( Route::setNbAnchor, 9)
//				            .with( Route::setVerticality, "Verticale")
//				            .with( Route::setCrag, crags.get( 0 ))
//				            .build());
//
//		routes.add( GenericBuilder.of(Route::new)
//				            .with( Route::setName, "La dalle" )
//				            .with( Route::setCreateDate, LocalDateTime.now())
//				            .with( Route::setGrade, "7a")
//				            .with( Route::setLength, 20)
//				            .with( Route::setNbAnchor, 9)
//				            .with( Route::setVerticality, "Verticale")
//				            .with( Route::setCrag, crags.get( 1 ))
//				            .build());
//
//		this.routeRepository.saveAll( routes );
//
//
//		//-----POPULATE PITCH
//
//		ArrayList< Pitch > pitches = new ArrayList<>();
//
//		pitches.add( GenericBuilder.of(Pitch::new)
//				             .with( Pitch::setName, "La petite fissure" )
//				             .with( Pitch::setCreateDate, LocalDateTime.now())
//				             .with( Pitch::setGrade, "6a")
//				             .with( Pitch::setLength, 10)
//				             .with( Pitch::setNbAnchor, 4)
//				             .with( Pitch::setVerticality, "Léger dévers")
//				             .with( Pitch::setRoute, routes.get( 0 ))
//				             .build());
//		pitches.add( GenericBuilder.of(Pitch::new)
//				             .with( Pitch::setName, "La grosse fissure" )
//				             .with( Pitch::setCreateDate, LocalDateTime.now())
//				             .with( Pitch::setGrade, "6c")
//				             .with( Pitch::setLength, 10)
//				             .with( Pitch::setNbAnchor, 4)
//				             .with( Pitch::setVerticality, "Gros dévers")
//				             .with( Pitch::setRoute, routes.get( 0 ))
//				             .build());
//
//		this.pitchRepository.saveAll( pitches );
//
//
//		//-----POPULATE COMMENT
//
//		List<Comment> comments = new ArrayList<>();
//
//		Comment comment1 = GenericBuilder.of( Comment::new )
//			                    .with( Comment::setUser, this.userRepository.findByUserName( "TheJohn" ) )
//								.with( Comment::setElement, this.atlasRepository.findByName( "Grimper en Lorraine" ) )
//			                    .with( Comment::setCreateDate, LocalDateTime.now())
//			                    .with( Comment::setContent, "En Lorraine on peut grimper mine de rien !! Maron c'est top pour apprendre.")
//			                    .with( Comment::setTitle, "Ca grimpe en Lorraine" )
//			                    .build();
//
//		comments.add( comment1 );
//
//		this.commentRepository.saveAll( comments );
//
//
//		//-----POPULATE BOOKINGREQUEST
//
//		List<BookingRequest> bookingRequests = new ArrayList<>();
//
//		bookingRequests.add( GenericBuilder.of( BookingRequest::new )
//				.with( BookingRequest::setCreateDate, LocalDateTime.now() )
//				.with( BookingRequest::setStartDate, LocalDate.of( 2018, 12, 1 ) )
//				.with( BookingRequest::setEndDate, LocalDate.of( 2018, 12, 10 ) )
//				.with( BookingRequest::setAtlas, atlas1 )
//				.with( BookingRequest::setUser, users.get( 1 ) )
//				.build());
//
//		this.bookingRequestRepository.saveAll( bookingRequests );
//
//	}
//
//}
//
//
