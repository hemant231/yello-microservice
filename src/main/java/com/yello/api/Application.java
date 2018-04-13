package com.yello.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is the main class with main method to boot the application.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 27-03-2018
 *
 */
@SpringBootApplication
@EnableWebMvc
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	/**
	 * This is main method to start spring Boot application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		logger.info("Booting Application..");
		//SpringApplication.run( Application.class , args);

		SpringApplication.run(new Object[] { Application.class }, args);
	}
}
