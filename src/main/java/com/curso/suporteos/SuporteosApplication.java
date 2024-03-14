package com.curso.suporteos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.curso.domains","com.curso.domains.enums"})
@SpringBootApplication
public class SuporteosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuporteosApplication.class, args);
	}

}
