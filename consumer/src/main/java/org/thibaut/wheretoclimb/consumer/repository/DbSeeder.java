package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.model.beans.Area;
import org.thibaut.wheretoclimb.model.beans.Atlas;
import org.thibaut.wheretoclimb.model.beans.Role;
import org.thibaut.wheretoclimb.model.beans.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class DbSeeder implements CommandLineRunner {

	private UserRepository userRepository;
	private AtlasRepository atlasRepository;
	private RoleRepository roleRepository;
	private AreaRepository areaRepository;

	public DbSeeder( UserRepository userRepository,
	                 AtlasRepository atlasRepository,
	                 RoleRepository roleRepository,
	                 AreaRepository areaRepository) {
		this.userRepository = userRepository;
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

		System.out.println( "DbSeeder RUN" );

		this.atlasRepository.deleteAll();
		this.userRepository.deleteAll();
		this.areaRepository.deleteAll();
//		this.roleRepository.deleteAll();

		//POPULATE ROLE
//		Collection< Role > roles = new ArrayList<>();
//
//		roles.add( new Role("ADMIN") );
//		roles.add( new Role("USER") );
//
//		this.roleRepository.saveAll( roles );

		//POPULATE AREA
		Collection< Area > areas = new ArrayList<Area>();

		areas.add( new Area("Maron", getDate(), null, null,
				null, null, 12,
				"au bord de la moselle, en amont de la ville de Maron", null));

		this.areaRepository.saveAll( areas );


		//POPULATE ATLAS
		Atlas atlas1 = new Atlas( "Grimper en Lorraine", getDate(),
				null, null, true);

		Collection< Atlas > atlases = new ArrayList<Atlas>();

		atlas1.setAreas( ( ArrayList< Area > ) areas );
		atlases.add( atlas1 );

		this.atlasRepository.saveAll( atlases );


		//POPULATE USERS
		ArrayList< User > users = new ArrayList<>(  );

		users.add( new User( "male", "John",
				"Doe", "Another", "1235",
				"john@gmail.com", getDate()));

		users.add( new User( "female", "Lea",
				"Corvoisier", "Lele", "1265",
				"lea@gmail.com", getDate() ));

		users.get( 1 ).setAtlases( atlases );

		users.add( new User( "male", "Admin",
				"Admin", "Admin", "123",
				"admin@gmail.com", getDate()));

		Role role = this.roleRepository.getOne( 1 );

		users.get( 2 ).setRole( role );


		this.userRepository.saveAll( users );

		System.out.println( userRepository.findUserFromEmail( "john@gmail.com","1235" ) );

	}

	public Date getDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = new Date (dtf.format(now));
		return date;
	}
}

