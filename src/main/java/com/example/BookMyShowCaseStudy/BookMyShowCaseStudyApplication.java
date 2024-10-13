package com.example.BookMyShowCaseStudy;

import Dtos.SignUpRequestDto;
import com.example.BookMyShowCaseStudy.Controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class BookMyShowCaseStudyApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowCaseStudyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDto requestDto = new SignUpRequestDto();
		requestDto.setEmail("vishal1@gmail.com");
		requestDto.setPassword("abc123");

		userController.signUp(requestDto);
	}
}
