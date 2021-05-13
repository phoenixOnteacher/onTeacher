package com.onteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // scheduler를 사용하기 위해 추가
public class OnTeacherProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnTeacherProjectApplication.class, args);
	}

}
