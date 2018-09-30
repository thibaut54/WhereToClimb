package org.thibaut.wheretoclimb.webapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;
import org.thibaut.wheretoclimb.consumer.repository.*;
import org.thibaut.wheretoclimb.model.beans.Area;
import org.thibaut.wheretoclimb.model.beans.Atlas;
import org.thibaut.wheretoclimb.model.beans.Role;
import org.thibaut.wheretoclimb.model.beans.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
	 * Callback used to run the beans.
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

		areas.add( new Area("Maron", getDate(), null, null,
				null, null, 12,
				"au bord de la moselle, en amont de la ville de Maron", null));

		this.areaRepository.saveAll( areas );


		//-----POPULATE USERS
		Collection< User > users = new ArrayList<>(  );

		//pwd = 1235
		users.add( new User( "male", "John",
				"Doe", "Another",
				"john@gmail.com", getDate()));
		( ( ArrayList< User> ) users ).get( 0 ).setPassword( passwordManager.crypt( "1235" ) );

		//pwd = 1265
		users.add( new User( "female", "Lea",
				"Corvoisier", "Lele",
				"lea@gmail.com", getDate() ));
		( ( ArrayList< User> ) users ).get( 1 ).setPassword( passwordManager.crypt( "1265" ) );

		//pwd = 123
		users.add( new User( "male", "Admin",
				"Admin", "Admin",
				"admin@gmail.com", getDate()));
		( ( ArrayList< User> ) users ).get( 2 ).setPassword( passwordManager.crypt( "123" ) );
//
//			//-----SET ROLE

		List< Role > rolesUser1 = new ArrayList<>();
		rolesUser1.add( this.roleRepository.findByRoleLike( "%USER" ) );

		( ( ArrayList< User> ) users ).get( 0 ).setUserRoles( rolesUser1 );
		( ( ArrayList< User> ) users ).get( 1 ).setUserRoles( rolesUser1 );

		List< Role > rolesUser3 = new ArrayList<>();
		rolesUser3.add( this.roleRepository.findByRoleLike( "%USER" ) );
		rolesUser3.add( this.roleRepository.findByRoleLike( "%ADMIN" ) );
		( ( ArrayList< User> ) users ).get( 2).setUserRoles( rolesUser3 );


		//-----SAVE ALL USERS

		this.userRepository.saveAll( users );

		//-----SET USER_ID IN ATLAS


		//-----POPULATE ATLAS
		Atlas atlas1 = new Atlas( "Grimper en Lorraine", "France", getDate(),
				null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas2 = new Atlas( "Grimper en Ile-de-France", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas3 = new Atlas( "Grimper en PACA", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas4 = new Atlas( "Grimper en Rhône-Alpes", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas5 = new Atlas( "Grimper en Bourgogne", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas6 = new Atlas( "Grimper en Bretagne", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas7 = new Atlas( "Grimper dans le Languedoc", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas8 = new Atlas( "Grimper dans le Centre", "France", getDate(),
						null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));
		Atlas atlas9 = new Atlas( "Grimper en ailleurs", "France", getDate(),
				null, null, true, ( ( ArrayList< User> ) users ).get( 0 ));

		Collection< Atlas > atlases = new ArrayList<Atlas>();

		atlas1.setAreas( ( ArrayList< Area > ) areas );
		atlases.add( atlas1 );
		atlases.add( atlas2 );
		atlases.add( atlas3 );
		atlases.add( atlas4 );
		atlases.add( atlas5 );
		atlases.add( atlas6 );
		atlases.add( atlas7 );
		atlases.add( atlas8 );
		atlases.add( atlas9 );

//		this.atlasRepository.saveAll( atlases );

		this.atlasRepository.saveAll( atlases );

		//Pourquoi ça ne fonctionne pas ?
//		this.atlasRepository.findByName( "Grimper en Lorraine" ).setUser( ( ( ArrayList< User> ) users ).get( 0 ) );



//		users.forEach( (user -> System.out.println( user.toString() ) ) );

//		List<String> test = this.roleRepository.findRoleByUserName( "Admin" );
//		test.forEach( System.out::println );


//		test.forEach( str-> System.out.println( str ) );

	}

	public Date getDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = new Date (dtf.format(now));
		return date;
	}
}


