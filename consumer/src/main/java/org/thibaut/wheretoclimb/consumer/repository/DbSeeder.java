package org.thibaut.wheretoclimb.consumer.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.model.bean.Climber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import rc.domain.Hotel;

@Component
public class DbSeeder implements CommandLineRunner {

	private ClimberRepository climberRepository;

	public DbSeeder( ClimberRepository climberRepository ) {
		this.climberRepository = climberRepository;
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

		Climber climber1 = new Climber( "male", "John",
				"Doe", "Another", "1235",
				"john@gmail.com",
				getDate());


		Climber climber2 = new Climber( "female", "Lea",
				"Corvoisier", "Lele", "1265",
				"lea@gmail.com", getDate() );



		List< Climber > climbers = new ArrayList<>(  );

//		for ( Climber climber: climbers ) {
//			climbers.add( climber );
//		}

		climbers.add( climber1 );
		climbers.add( climber2 );

		this.climberRepository.saveAll( climbers );

		System.out.println( climberRepository.findUserFromEmail( "john@gmail.com","1235" ) );

	}

	public Date getDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Date date = new Date (dtf.format(now));
		return date;
	}
}

