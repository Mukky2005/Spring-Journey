package com.mukesh.firstpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

@SpringBootApplication
@RestController
public class FirstProApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(FirstProApplication.class, args);
	}
	@GetMapping
	public String hw(){
		return "hw";
	}

}
