package org.thibaut.wheretoclimb.webapp;

import groovy.util.logging.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;
import org.thibaut.wheretoclimb.consumer.repository.*;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.util.GenericBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class TestApplication implements CommandLineRunner {

	private PasswordManager passwordManager;
	private AtlasManager atlasManager;
	private UserRepository userRepository;
	private ElementRepository elementRepository;
	private AtlasRepository atlasRepository;
	private RoleRepository roleRepository;
	private AreaRepository areaRepository;
	private MessageRepository messageRepository;
	private CommentRepository commentRepository;
	private BookingRequestRepository bookingRequestRepository;


	public TestApplication( PasswordManager passwordManager, AtlasManager atlasManager, UserRepository userRepository, ElementRepository elementRepository, AtlasRepository atlasRepository, RoleRepository roleRepository, AreaRepository areaRepository, MessageRepository messageRepository, CommentRepository commentRepository, BookingRequestRepository bookingRequestRepository ) {
		this.passwordManager = passwordManager;
		this.atlasManager = atlasManager;
		this.userRepository = userRepository;
		this.elementRepository = elementRepository;
		this.atlasRepository = atlasRepository;
		this.roleRepository = roleRepository;
		this.areaRepository = areaRepository;
		this.messageRepository = messageRepository;
		this.commentRepository = commentRepository;
		this.bookingRequestRepository = bookingRequestRepository;
	}

	/**
	 * Callback used to run the entity.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run( String... args ) throws Exception {

		System.out.println( "CONSUMER APP RUN" );

		//-----CLEAN DB
		this.atlasRepository.deleteAll();
		this.areaRepository.deleteAll();
		this.elementRepository.deleteAll();
		this.messageRepository.deleteAll();
		this.commentRepository.deleteAll();
		this.userRepository.deleteAll();
		this.roleRepository.deleteAll();

//		this.elementRepository.flush();
//		this.messageRepository.flush();
//		this.commentRepository.flush();
//		this.userRepository.flush();
//		this.roleRepository.flush();
//		this.areaRepository.flush();
//		this.atlasRepository.flush();


		//-----POPULATE USER_ROLES

		Collection<Role> roles = new ArrayList<>();

		roles.add(new Role("ROLE_USER"));
		roles.add(new Role("ROLE_ADMIN"));

		this.roleRepository.saveAll( roles );


		//-----POPULATE AREA

		ArrayList< Area > areas = new ArrayList<Area>();
		ArrayList< Area > areas2 = new ArrayList<Area>();

		areas.add( GenericBuilder.of( Area::new )
							.with( Area::setName, "Maron" )
							.with( Area::setCreateDate, LocalDateTime.now() )
							.with( Area::setUpdateDate, LocalDateTime.now() )
							.with( Area::setApproachDuration, 12 )
							.with( Area::setLocality, "Au bord de la Moselle, 4 km en amont de la ville de Maron" )
							.build());
		areas.add( GenericBuilder.of( Area::new )
							.with( Area::setName, "Liverdun" )
							.with( Area::setCreateDate, LocalDateTime.now() )
							.with( Area::setUpdateDate, LocalDateTime.now() )
							.with( Area::setApproachDuration, 5 )
							.with( Area::setLocality, "Vers Liverdun, quelque part, il faut chercher !" )
							.build());
		areas2.add( GenericBuilder.of( Area::new )
				           .with( Area::setName, "Fontainebleau" )
				           .with( Area::setCreateDate, LocalDateTime.now() )
				           .with( Area::setUpdateDate, LocalDateTime.now() )
				           .with( Area::setApproachDuration, 12 )
				           .with( Area::setLocality, "Dans la forêt de fontainebleau" )
				           .build());
		areas2.add( GenericBuilder.of( Area::new )
				           .with( Area::setName, "SecteurTest" )
				           .with( Area::setCreateDate, LocalDateTime.now() )
				           .with( Area::setUpdateDate, LocalDateTime.now() )
				           .with( Area::setApproachDuration, 5 )
				           .with( Area::setLocality, "Vers un endroit, quelque part, il faut bien chercher !" )
				           .build());

		this.areaRepository.saveAll( areas );
		this.areaRepository.saveAll( areas2 );


		//-----POPULATE USERS

		List< User> users = new ArrayList<>(  );

		//pwd = 1235
		users.add( GenericBuilder.of( User::new )
							.with(User::setGender, "male")
							.with(User::setFirstName, "John")
							.with(User::setLastName, "Doe")
							.with(User::setUserName, "Another")
							.with(User::setEmail, "john@gmail.com")
							.with(User::setCreateAccountDate, LocalDateTime.now())
							.with(User::setPassword, passwordManager.crypt( "1235"))
							.build());

		//pwd = 1265
		users.add( GenericBuilder.of( User::new )
				           .with(User::setGender, "female")
				           .with(User::setFirstName, "Lea")
				           .with(User::setLastName, "Corvoisier")
				           .with(User::setUserName, "Lele")
				           .with(User::setEmail, "lea@gmail.com")
				           .with(User::setCreateAccountDate, LocalDateTime.now())
				           .with(User::setPassword, passwordManager.crypt( "1265"))
				           .build());

		//pwd = 123
		users.add( GenericBuilder.of( User::new )
				           .with(User::setGender, "male")
				           .with(User::setFirstName, "Admin")
				           .with(User::setLastName, "Admin")
				           .with(User::setUserName, "Admin")
				           .with(User::setEmail, "admin@gmail.com")
				           .with(User::setCreateAccountDate, LocalDateTime.now())
				           .with(User::setPassword, passwordManager.crypt( "123"))
				           .build());


		//-----SET ROLE

		List< Role > rolesUser1 = new ArrayList<>();
		rolesUser1.add( this.roleRepository.findByRoleLike( "%USER" ) );

		users.get( 0 ).setRoles( rolesUser1 );
		users.get( 1 ).setRoles( rolesUser1 );

		List< Role > rolesUser3 = new ArrayList<>();
		rolesUser3.add( this.roleRepository.findByRoleLike( "%USER" ) );
		rolesUser3.add( this.roleRepository.findByRoleLike( "%ADMIN" ) );
		users.get( 2).setRoles( rolesUser3 );


		//-----SAVE ALL USERS

		this.userRepository.saveAll( users );


		//-----POPULATE ATLAS

		Atlas atlas1 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Lorraine" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, false)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, users.get( 0 ))
								.build();
		Atlas atlas2 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region1" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "USA")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Rhône-Alpes")
								.with( Atlas::setUser, users.get( 0 ))
								.build();
		Atlas atlas3 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region2" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "MOON")
								.with( Atlas::setScale, "Rhône-Alpes")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, users.get( 0 ))
								.build();
		Atlas atlas4 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region3" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, false)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "PACA")
								.with( Atlas::setUser, users.get( 1 ))
								.build();
		Atlas atlas5 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region4" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "PACA")
								.with( Atlas::setUser, users.get( 1 ))
								.build();
		Atlas atlas6 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region5" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Languedoc-Roussillon")
								.with( Atlas::setUser, users.get( 1 ))
								.build();
		Atlas atlas7 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region6" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Languedoc-Roussillon")
								.with( Atlas::setUser, users.get( 2 ))
								.build();
		Atlas atlas8 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region7" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, false)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Midi-Pyrénées")
								.with( Atlas::setUser, users.get( 2 ))
								.build();
		Atlas atlas9 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region8" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Midi-Pyrénées")
								.with( Atlas::setUser, users.get( 2 ))
								.build();
		Atlas atlas10 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region9" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Midi-Pyrénées")
								.with( Atlas::setUser, users.get( 2 ))
								.build();
		Atlas atlas11 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region10" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, false)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "PACA")
								.with( Atlas::setUser, users.get( 2 ))
								.build();



		List< Atlas > atlases = new ArrayList<Atlas>();

		atlas1.setAreas( areas );
		atlas2.setAreas( areas2 );
		atlases.add( atlas1 );
		atlases.add( atlas2 );
		atlases.add( atlas3 );
		atlases.add( atlas4 );
		atlases.add( atlas5 );
		atlases.add( atlas6 );
		atlases.add( atlas7 );
		atlases.add( atlas8 );
		atlases.add( atlas9 );
		atlases.add( atlas10 );
		atlases.add( atlas11 );


		this.atlasRepository.saveAll( atlases );

//		QAtlas qAtlas = QAtlas.atlas;
//
//		List <Atlas> atlases1 = (this.atlasManager.searchAtlasByNameAndCountryAndRegionAndDepartment( 0 , 5 , "Grimper" , "France" , null, null  )).getContent();
//
//		System.out.println( " TEST REQUETE QUERYDSL" );
//		System.out.println( "Taille de la liste :" + atlases1.size() );
//		atlases1.forEach( System.out::println );
//		System.out.println( " FIN DU TEST REQUETE QUERYDSL" );

		//-----POPULATE MESSAGE

		List< Message > messages = new ArrayList<>();

		Message message1 = GenericBuilder.of( Message::new )
								.with( Message::setContent, "Salut, qu'a tu pensé de Maron ?" )
								.with( Message::setCreateDate, LocalDateTime.now())
								.with( Message::setUserEmitter, this.userRepository.findByUserName( "Lele" ) )
								.with( Message::setUserRecipient, this.userRepository.findByUserName( "Another" ) )
								.build();

		messages.add( message1 );

		this.messageRepository.saveAll( messages );


		//-----POPULATE COMMENT

		List<Comment> comments = new ArrayList<>();

		Comment comment1 = GenericBuilder.of( Comment::new )
			                    .with( Comment::setUserEmitter, this.userRepository.findByUserName( "Another" ) )
								.with( Comment::setElement, this.atlasRepository.findByName( "Grimper en Lorraine" ) )
			                    .with( Comment::setCreateDate, LocalDateTime.now())
			                    .with( Comment::setContent, "En Lorraine on peut grimper mine de rien !! Maron c'est top pour apprendre.")
			                    .with( Comment::setTitle, "Ca grimpe en Lorraine" )
			                    .build();

		comments.add( comment1 );

		this.commentRepository.saveAll( comments );


		//-----POPULATE BOOKINGREQUEST

		List<BookingRequest> bookingRequests = new ArrayList<>();

		BookingRequest bookingRequest = GenericBuilder.of( BookingRequest::new )
				.with( BookingRequest::setCreateDate, LocalDateTime.now() )
				.with( BookingRequest::setStartDate, LocalDate.of( 2018, 12, 01 ) )
				.with( BookingRequest::setEndDate, LocalDate.of( 2018, 12, 10 ) )
				.with( BookingRequest::setAtlas, atlas2 )
				.with( BookingRequest::setUserEmitter, users.get( 2 ) )
				.build();

		this.bookingRequestRepository.saveAll( bookingRequests );


		//Pourquoi ça ne fonctionne pas ?
//		this.atlasRepository.findByName( "Grimper en Lorraine" ).setUser( ( ( ArrayList< User> ) users ).get( 0 ) );



//		users.forEach( (user -> System.out.println( user.toString() ) ) );

//		List<String> test = this.roleRepository.findRoleByUserName( "Admin" );
//		test.forEach( System.out::println );


//		test.forEach( str-> System.out.println( str ) );

	}

}


