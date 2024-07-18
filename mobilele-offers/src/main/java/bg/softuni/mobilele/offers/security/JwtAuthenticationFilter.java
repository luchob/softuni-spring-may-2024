package bg.softuni.mobilele.offers.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");

    //Authorization: Bearer <token>
    if (authHeader == null ||
        authHeader.isBlank() ||
        !authHeader.startsWith("Bearer ")
    ) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authHeader.substring(7);

    // TODO: Finalize the filter and parse the token
  }
}
