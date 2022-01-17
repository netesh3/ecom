package com.nik.code.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ECOMApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECOMApplication.class, args);
	}

}
