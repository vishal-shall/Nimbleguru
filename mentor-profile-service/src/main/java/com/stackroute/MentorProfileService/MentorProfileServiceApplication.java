package com.stackroute.MentorProfileService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MentorProfileServiceApplication
{
	public static void main(String[] args) {
		SpringApplication.run(MentorProfileServiceApplication.class, args);
	}

}
