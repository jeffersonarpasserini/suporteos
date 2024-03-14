package com.curso.suporteos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = {"com.curso.domains","com.curso.domains.enums"})
//@ComponentScan(basePackages = "com.curso.services")
@SpringBootApplication
public class SuporteosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuporteosApplication.class, args);
	}

}
