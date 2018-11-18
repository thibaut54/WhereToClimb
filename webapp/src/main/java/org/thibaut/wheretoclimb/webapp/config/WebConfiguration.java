package org.thibaut.wheretoclimb.webapp.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// Load file: validation.properties
		messageSource.setBasename("classpath:validation");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 *
	 * @return a bean with the layoutDialect
	 */
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Override
	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry
				.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("/webjars/");
	}


//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine engine  =  new SpringTemplateEngine();
//
//		final Set< IDialect > dialects = new HashSet<IDialect>();
//		dialects.add( new SpringSecurityDialect() );
//		engine.setDialects( dialects );
//
//		return engine;
//	}
}
