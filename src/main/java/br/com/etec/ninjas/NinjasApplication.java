package br.com.etec.ninjas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NinjasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinjasApplication.class, args);
	}

}


// mvn spring-boot:run         para iniciar o codigo via terminal
// mvn clean install -U        coloca no terminal depois de botar uma dependencia

//   lombok Service