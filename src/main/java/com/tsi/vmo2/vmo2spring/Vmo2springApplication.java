package com.tsi.vmo2.vmo2spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class Vmo2springApplication {

	public static void main(String[] args) {
		SpringApplication.run(Vmo2springApplication.class, args);
	}


}

