package nl.novi.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EgineRightApplication{

	public static void main(String[] args){
		SpringApplication.run(EgineRightApplication.class, args);
	}



}
