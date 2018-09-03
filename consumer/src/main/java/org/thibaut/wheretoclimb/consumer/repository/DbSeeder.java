package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.model.bean.Atlas;
import org.thibaut.wheretoclimb.model.bean.Climber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//import rc.domain.Hotel;

@Component
public class DbSeeder implements CommandLineRunner {

	private ClimberRepository climberRepository;
	private AtlasRepository atlasRepository;

	public DbSeeder( ClimberRepository climberRepository, AtlasRepository atlasRepository ) {
		this.climberRepository = climberRepository;
		this.atlasRepository = atlasRepository;
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run( String... args ) throws Exception {

		System.out.println( "DbSeeder RUN" );

		Collection< Climber > climbers = new ArrayList<>(  );

		climbers.add( new Climber( "male", "John",
				"Doe", "Another", "1235",
				"john@gmail.com", getDate()));


		Climber climber2 = new Climber( "female", "Lea",
				"Corvoisier", "Lele", "1265",
				"lea@gmail.com", getDate() );

		Atlas atlas1 = new Atlas( "Grimper en Lorraine", getDate(),
				null, null, true);

		Collection< Atlas > atlases = new ArrayList<Atlas>();

		atlases.add( atlas1 );

		climber2.setAtlases( ( ArrayList< Atlas > ) atlases );

		this.atlasRepository.saveAll( atlases );

//		climbers.add( climber1 );
		climbers.add( climber2 );

		this.climberRepository.saveAll( climbers );
		this.atlasRepository.deleteAll( atlases );

		System.out.println( climberRepository.findUserFromEmail( "john@gmail.com","1235" ) );

	}

	public Date getDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = new Date (dtf.format(now));
		return date;
	}
}

