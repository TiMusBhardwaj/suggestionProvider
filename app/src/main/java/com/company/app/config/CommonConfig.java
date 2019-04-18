package com.company.app.config;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.company.app.controller.AppController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This Class will provide common configuration
 * 
 * @author sumit.bhardwaj
 *
 */
@Configuration
@EnableJpaRepositories("com.company.domain.configdata.repository")
@EntityScan("com.company.domain.configdata.entity")
@EnableSwagger2
public class CommonConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.company.app.controller"))
				.paths(PathSelectors.ant("/suggest_cities_count*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("City Information API", "City Information API", "v1", "Terms of service",
				new Contact("Sumit Bhardwaj", "www.company.com", "sumit.bhardwaj@company.com"), "License of API",
				"API license URL", Collections.emptyList());
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			if (!logger.isDebugEnabled()) {
				return;
			}
			logger.debug("Beans Loaded by Spring-------------");

			String[] beanNames = ctx.getBeanDefinitionNames();
			for (String beanName : beanNames) {
				logger.debug(beanName);
			}
			logger.debug("---------------------------------");
		};
	}

}
