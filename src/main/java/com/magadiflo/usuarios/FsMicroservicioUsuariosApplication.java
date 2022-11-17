package com.magadiflo.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient // Le decimos que será un cliente de Eureka
@EntityScan({ "com.magadiflo.commons.alumnos.models.entity" }) // Le decimos que nuestras entidades lo escanee de este package, que es el de nuestra librería commons alumnos
@SpringBootApplication
public class FsMicroservicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsMicroservicioUsuariosApplication.class, args);
	}

}
