package com.salesianostriana.dam;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info =
@Info(description = "Una API REST para gestionar talleres culturales, sus organizadores y participantes",
		version = "1.0",
		contact = @Contact(email = "martin.cagui25@triana.salesianos.edu", name = "Guille"),
		license = @License(name = "CC BY"),
		title = "API de talleres culturales"
)
)
@SpringBootApplication
public class DamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DamApplication.class, args);
	}

}
