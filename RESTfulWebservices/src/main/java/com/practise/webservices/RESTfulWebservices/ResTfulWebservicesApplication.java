package com.practise.webservices.RESTfulWebservices;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.practise.webservices.RESTfulWebservices.HelloWorld.HelloWorldBean;
import com.practise.webservices.RESTfulWebservices.HelloWorld.HelloWorldController;

import ch.qos.logback.classic.Logger;
@SpringBootApplication
@ComponentScan
public class ResTfulWebservicesApplication {

	private static Logger logger = (Logger) LoggerFactory.getLogger(ResTfulWebservicesApplication.class);
	
	public static void main(String[] args) {
		
		logger.debug("Started");
		
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldController.class);
		
		//HelloWorldBean hello = context.getBean(HelloWorldBean.class);
		SpringApplication.run(ResTfulWebservicesApplication.class, args);
	}

}
