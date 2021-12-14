package br.com.leopoldodev.api;

import br.com.leopoldodev.api.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class ApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);

	}

}
