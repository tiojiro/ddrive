package com.dswiss.api.ddrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DdriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdriveApplication.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "Server Ok!";
	}

}
