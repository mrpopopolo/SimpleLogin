package fr.pops.spring.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "fr.pops.spring.login.business.entity",
				 "fr.pops.spring.login.business.service",
				 "fr.pops.spring.login.persistence",
				 "fr.pops.spring.login.presentation",
				 "fr.pops.spring.login.configuration"
				})	
@SpringBootApplication()
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
