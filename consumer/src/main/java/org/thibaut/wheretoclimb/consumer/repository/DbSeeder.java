//package org.thibaut.wheretoclimb.consumer.repository;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.thibaut.wheretoclimb.model.beans.Area;
//import org.thibaut.wheretoclimb.model.beans.Atlas;
//import org.thibaut.wheretoclimb.model.beans.UserRole;
//import org.thibaut.wheretoclimb.model.beans.User;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//@Component
//public class DbSeeder implements CommandLineRunner {
//
//	private UserRepository userRepository;
//	private AtlasRepository atlasRepository;
//	private UserRoleRepository userRoleRepository;
//	private AreaRepository areaRepository;
//
//	public DbSeeder( UserRepository userRepository,
//	                 AtlasRepository atlasRepository,
//	                 UserRoleRepository userRoleRepository,
//	                 AreaRepository areaRepository) {
//		this.userRepository = userRepository;
//		this.atlasRepository = atlasRepository;
//		this.userRoleRepository = userRoleRepository;
//		this.areaRepository = areaRepository;
//	}
//
//	/**
//	 * Callback used to run the beans.
//	 *
//	 * @param args incoming main method arguments
//	 * @throws Exception on error
//	 */
//	@Override
//	public void run( String... args ) throws Exception {
//
//		System.out.println( "DbSeeder RUN" );
//
//		//-----CLEAN DB
//		this.atlasRepository.deleteAll();
//		this.areaRepository.deleteAll();
//		this.userRoleRepository.deleteAll();
//		this.userRepository.deleteAll();
//
//
//		//-----POPULATE USER_ROLES
//		Collection<UserRole> userRoles = new ArrayList<>();
//
//		userRoles.add( new UserRole("ADMIN"));
//
//		this.userRoleRepository.saveAll( userRoles );
//
//
//		//-----POPULATE AREA
//		Collection< Area > areas = new ArrayList<Area>();
//
//		areas.add( new Area("Maron", getDate(), null, null,
//				null, null, 12,
//				"au bord de la moselle, en amont de la ville de Maron", null));
//
//		this.areaRepository.saveAll( areas );
//
//
//		//-----POPULATE ATLAS
//		Atlas atlas1 = new Atlas( "Grimper en Lorraine", getDate(),
//				null, null, true);
//
//		Collection< Atlas > atlases = new ArrayList<Atlas>();
//
//		atlas1.setAreas( ( ArrayList< Area > ) areas );
//		atlases.add( atlas1 );
//
//		this.atlasRepository.saveAll( atlases );
//
//
//		//-----POPULATE USERS
//		Collection< User > users = new ArrayList<>(  );
//
//		users.add( new User( "male", "John",
//				"Doe", "Another", "1235",
//				"john@gmail.com", getDate()));
//
//		users.add( new User( "female", "Lea",
//				"Corvoisier", "Lele", "1265",
//				"lea@gmail.com", getDate() ));
//
//		( ( ArrayList< User> ) users ).get( 1 ).setAtlases( atlases );
//
//		users.add( new User( "male", "Admin",
//				"Admin", "Admin", "123",
//				"admin@gmail.com", getDate()));
//
//			//-----SET ROLE
//		( ( ArrayList< User> ) users ).get( 2 ).setUserRoles( userRoles );
//
//
//		//-----SAVE ALL USERS
//		this.userRepository.saveAll( users );
//
////		users.forEach( (user -> System.out.println( user.toString() ) ) );
//
//
//	}
//
//	public Date getDate(){
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		Date date = new Date (dtf.format(now));
//		return date;
//	}
//}
//
