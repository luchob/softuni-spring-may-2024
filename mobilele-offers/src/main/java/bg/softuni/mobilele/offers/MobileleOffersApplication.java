package bg.softuni.mobilele.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class MobileleOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileleOffersApplication.class, args);
	}

}
