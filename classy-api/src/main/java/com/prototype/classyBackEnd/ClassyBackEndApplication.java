package com.prototype.classyBackEnd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class ClassyBackEndApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.properties,"
			+ "classpath:aws.yml";

	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata","true");
	}

	public static void main(String[] args) {
		SpringApplication.run(ClassyBackEndApplication.class, args);
		//new SpringApplicationBuilder(ClassyBackEndApplication.class).properties(APPLICATION_LOCATIONS).run(args);
	}

}
