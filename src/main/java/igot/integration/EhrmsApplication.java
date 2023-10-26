package igot.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "igot.integration")
public class EhrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EhrmsApplication.class, args);
	}


}
