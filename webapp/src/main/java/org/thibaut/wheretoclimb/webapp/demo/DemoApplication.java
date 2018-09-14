package org.thibaut.wheretoclimb.webapp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.beans.User;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.thibaut.wheretoclimb"})
@EntityScan(basePackages = {"org.thibaut.wheretoclimb"})
@ComponentScan(basePackages = {"org.thibaut.wheretoclimb"})
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run( DemoApplication.class, args );


	}
}
