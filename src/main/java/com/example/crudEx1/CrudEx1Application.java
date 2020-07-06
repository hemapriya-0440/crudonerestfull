package com.example.crudEx1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/* @EnableSwagger2 */
public class CrudEx1Application {

	public static void main(String[] args) {
		SpringApplication.run(CrudEx1Application.class, args);
	}
	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
	 * .build(); }
	 */                  
}
