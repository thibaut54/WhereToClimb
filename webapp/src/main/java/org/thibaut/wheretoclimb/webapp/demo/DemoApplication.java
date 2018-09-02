package org.thibaut.wheretoclimb.webapp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = { "org.thibaut.ougrimper.consumer.seeder" })
//@EntityScan(basePackages = {"org.thibaut.ougrimper.model"})
//@ComponentScan(basePackages = {"org.thibaut.ougrimper.consumer"})
@EnableJpaRepositories(basePackages = {"org.thibaut.wheretoclimb"})
@EntityScan(basePackages = {"org.thibaut.wheretoclimb"})
@ComponentScan(basePackages = {"org.thibaut.wheretoclimb"})
public class DemoApplication {

	public static void main(String[] args){

		/*ApplicationContext ctx = */ SpringApplication.run( DemoApplication.class , args);

//		ClassTest ct = ctx.getBean( ClassTest.class );
//
//		ct.test();
//
//		UserRepository userRepository = ctx.getBean( UserRepository.class );
//
//		User user1 = new User( "male", "John",
//				"Doe", "Another", "1235",
//				"john@gmail.com", null, null, null,
//				getDate(), null );
//
//		userRepository.save(user1);
//
//		User user2 = new User( "female", "Lea",
//				"Corvoisier", "Lele", "1265",
//				"lea@gmail.com", null, null, null,
//				getDate(), null );
//
//		userRepository.save(user2);
//
//		userRepository.findAll().forEach( user -> System.out.println( user.getUserName() ) );
//
////		List<User> userList = new ArrayList<>(  );
////		userList.add(user1);
////		userList.add(user2);
////
////		userRepository.saveAll( userList );
	}
//
//	public static String getDate( ){
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//		return dtf.format(now);
//	}
}
