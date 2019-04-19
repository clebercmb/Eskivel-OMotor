/*
 * Product: OMotor
 * Copyright (C) 2017 OMotor. All Rights Reserved.
 */
package com.test.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;




/**
 * Start da aplicação
 * @author esdrastavares
 *
 */
@SpringBootApplication
@ComponentScan({
	"com.test.service",
	"com.test.api.resources",
	"com.test.config.user",
	"com.test.config.amqp"
})
public class TestMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestMain.class);
	
	public static void main(String[] args) {
		LOGGER.debug("TestMain - Inicio 2");
		//SpringApplication.run(TestMain.class, args);
		
		final SpringApplication app = new SpringApplication(TestMain.class);
		final ConfigurableApplicationContext context = app.run(args);
		
		ConfigurableEnvironment env = context.getEnvironment();
		
		String host = env.getProperty("SPRING_RABBITMQ_HOST", env.getProperty("spring.rabbitmq.host"));

		LOGGER.debug(">>>main().host="+host);

		
	}

}



