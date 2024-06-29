package com.ipn.mx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnosApplication.class, args);
	}
	 
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
                .allowedOrigins("*")  // Permitir solicitudes desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*");  // Encabezados permitidos
			}
		};
	}
	
	
}

