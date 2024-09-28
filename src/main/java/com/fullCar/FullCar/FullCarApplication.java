package com.fullCar.FullCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FullCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullCarApplication.class, args);
		System.out.println("FullCar backend is running in port:8080");
	}

}
