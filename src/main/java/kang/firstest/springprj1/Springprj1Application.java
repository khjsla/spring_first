package kang.firstest.springprj1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Springprj1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springprj1Application.class, args);
	}

}
