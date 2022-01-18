package com.ruggero.booklibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@OpenAPIDefinition(info = @Info(title = "BookLibraryAPI", version = "1.0", description = "This API is for books"))
public class BookLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookLibraryApplication.class, args);
	}

}
