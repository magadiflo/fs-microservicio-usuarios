package com.magadiflo.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // Le decimos que será un cliente de Eureka
@SpringBootApplication
public class FsMicroservicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsMicroservicioUsuariosApplication.class, args);
	}

}
