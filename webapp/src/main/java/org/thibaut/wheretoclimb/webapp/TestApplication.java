package org.thibaut.wheretoclimb.webapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;
import org.thibaut.wheretoclimb.consumer.repository.*;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.Role;
import org.thibaut.wheretoclimb.model.entity.User;
import org.thibaut.wheretoclimb.util.GenericBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TestApplication implements CommandLineRunner {

	private PasswordManager passwordManager;
	private UserRepository userRepository;
	private ElementRepository elementRepository;
	private AtlasRepository atlasRepository;
	private RoleRepository roleRepository;
	private AreaRepository areaRepository;

	public TestApplication( PasswordManager passwordManager, UserRepository userRepository, ElementRepository elementRepository, AtlasRepository atlasRepository, RoleRepository roleRepository, AreaRepository areaRepository ) {
		this.passwordManager = passwordManager;
		this.userRepository = userRepository;
		this.elementRepository = elementRepository;
		this.atlasRepository = atlasRepository;
		this.roleRepository = roleRepository;
		this.areaRepository = areaRepository;
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
		this.elementRepository.deleteAll();
		this.userRepository.deleteAll();
		this.roleRepository.deleteAll();


		//-----POPULATE USER_ROLES

		Collection<Role> roles = new ArrayList<>();

		roles.add(new Role("ROLE_USER"));
		roles.add(new Role("ROLE_ADMIN"));

		this.roleRepository.saveAll( roles );


		//-----POPULATE AREA
		Collection< Area > areas = new ArrayList<Area>();
		Collection< Area > areas2 = new ArrayList<Area>();

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
		Collection< User > users = new ArrayList<>(  );

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

		( ( ArrayList< User> ) users ).get( 0 ).setRoles( rolesUser1 );
		( ( ArrayList< User> ) users ).get( 1 ).setRoles( rolesUser1 );

		List< Role > rolesUser3 = new ArrayList<>();
		rolesUser3.add( this.roleRepository.findByRoleLike( "%USER" ) );
		rolesUser3.add( this.roleRepository.findByRoleLike( "%ADMIN" ) );
		( ( ArrayList< User> ) users ).get( 2).setRoles( rolesUser3 );


		//-----SAVE ALL USERS

		this.userRepository.saveAll( users );

		//-----SET USER_ID IN ATLAS


		//-----POPULATE ATLAS

		Atlas atlas1 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Lorraine" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 0 ))
								.build();
		Atlas atlas2 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region1" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 0 ))
								.build();
		Atlas atlas3 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region2" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 0 ))
								.build();
		Atlas atlas4 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region3" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 1 ))
								.build();
		Atlas atlas5 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region4" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 1 ))
								.build();
		Atlas atlas6 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region5" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 1 ))
								.build();
		Atlas atlas7 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region6" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 2 ))
								.build();
		Atlas atlas8 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region7" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 2 ))
								.build();
		Atlas atlas9 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region8" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 2 ))
								.build();
		Atlas atlas10 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region9" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 2 ))
								.build();
		Atlas atlas11 = GenericBuilder.of( Atlas::new )
								.with( Atlas::setName, "Grimper en Region10" )
								.with( Atlas::setCreateDate, LocalDateTime.now() )
								.with( Atlas::setAvailable, true)
								.with( Atlas::setCountry, "France")
								.with( Atlas::setScale, "Regional")
								.with( Atlas::setRegion, "Lorraine")
								.with( Atlas::setUser, ((ArrayList< User> ) users ).get( 2 ))
								.build();





		Collection< Atlas > atlases = new ArrayList<Atlas>();

		atlas1.setAreas( ( ArrayList< Area > ) areas );
		atlas2.setAreas( ( ArrayList< Area > ) areas2 );
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

		//Pourquoi ça ne fonctionne pas ?
//		this.atlasRepository.findByName( "Grimper en Lorraine" ).setUser( ( ( ArrayList< User> ) users ).get( 0 ) );



//		users.forEach( (user -> System.out.println( user.toString() ) ) );

//		List<String> test = this.roleRepository.findRoleByUserName( "Admin" );
//		test.forEach( System.out::println );


//		test.forEach( str-> System.out.println( str ) );

	}

}


