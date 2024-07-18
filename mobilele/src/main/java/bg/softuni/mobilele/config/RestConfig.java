package bg.softuni.mobilele.config;

import bg.softuni.mobilele.service.JwtService;
import bg.softuni.mobilele.service.UserService;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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
      ClientHttpRequestInterceptor bearerToken) {
    return RestClient
        .builder()
        .baseUrl(offersApiConfig.getBaseUrl())
        .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .requestInterceptor(bearerToken)
        .build();
  }

  @Bean
  public ClientHttpRequestInterceptor bearerToken(
      JwtService jwtService,
      UserService userService) {
    return (r, b, e) -> {

      userService
          .getCurrentUser()
          .ifPresent(ud -> {
                String token = jwtService.generateToken(ud.getUsername(), Map.of("roles",
                    ud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()));
                r.getHeaders().setBearerAuth(token);
              }
          );


      return e.execute(r, b);
    };
  }

}
