package bg.softuni.mobilele.config;

import bg.softuni.mobilele.service.JwtService;
import bg.softuni.mobilele.service.UserService;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

  @Bean("genericRestClient")
  public RestClient genericRestClient() {
    return RestClient.create();
  }

  @Bean("offersRestClient")
  public RestClient offersRestClient(OfferApiConfig offersApiConfig,
      ClientHttpRequestInterceptor requestInterceptor) {
    return RestClient
        .builder()
        .baseUrl(offersApiConfig.getBaseUrl())
        .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .requestInterceptor(requestInterceptor)
        .build();
  }

  @Bean
  public ClientHttpRequestInterceptor requestInterceptor(UserService userService,
      JwtService jwtService) {
    return (r, b, e) -> {
      // put the logged user details into bearer token
      userService
          .getCurrentUser()
          .ifPresent(mud -> {
            String bearerToken = jwtService.generateToken(
                mud.getUuid().toString(),//
                Map.of(
                    "roles",
                    mud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                    "user",
                    mud.getUuid().toString()
                )
            );
            r.getHeaders().setBearerAuth(bearerToken);
          });

      return e.execute(r, b);
    };
  }

}
