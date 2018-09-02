package com.rest.service.cachingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author  Naresh
 *  this class used to run application
 */
@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
public class CachingServiceApplication {

	static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext  = SpringApplication.run(CachingServiceApplication.class, args);
	}

}
