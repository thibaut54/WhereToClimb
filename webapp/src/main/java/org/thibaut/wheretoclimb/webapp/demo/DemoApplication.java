package org.thibaut.wheretoclimb.webapp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = { "org.thibaut.wheretoclimb.consumer.repository" })
//@EntityScan(basePackages = {"org.thibaut.wheretoclimb.model.beans"})
//@ComponentScan(basePackages = {"org.thibaut.wheretoclimb"})
@EnableJpaRepositories(basePackages = {"org.thibaut.wheretoclimb"})
@EntityScan(basePackages = {"org.thibaut.wheretoclimb"})
@ComponentScan(basePackages = {"org.thibaut.wheretoclimb"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run( DemoApplication.class, args );
	}
}