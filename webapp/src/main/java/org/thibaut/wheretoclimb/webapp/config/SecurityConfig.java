//package org.thibaut.wheretoclimb.webapp.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Override
//	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
//		super.configure( auth );
//		auth.jdbcAuthentication()
//				.dataSource( dataSource )
//				.usersByUsernameQuery( "SELECT email as principal, password as credentials" +
//						                       "FROM users WHERE email=?" )
//				.authoritiesByUsernameQuery( "SELECT user_email as principal, role_label as role" +
//						                             "FROM roles_of_users WHERE user_email=?" )
//				/*.rolePrefix( "ROLE_" )*/;
//
//	}
//}
