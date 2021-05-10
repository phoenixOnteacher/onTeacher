package com.onteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnTeacherProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnTeacherProjectApplication.class, args);
	}

}
