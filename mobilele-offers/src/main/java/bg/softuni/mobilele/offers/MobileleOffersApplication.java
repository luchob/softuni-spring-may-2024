package bg.softuni.mobilele.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class MobileleOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileleOffersApplication.class, args);
	}

}
