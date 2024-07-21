package bg.softuni.mobilele.offers.security;

import bg.softuni.mobilele.offers.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  public JwtAuthenticationFilter(JwtService jwtService ) {
    this.jwtService = jwtService;
  }

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


    var userDetails = jwtService.extractUserInformation(jwt);

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
      );
      //authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      context.setAuthentication(authToken);
      SecurityContextHolder.setContext(context);
    }
    filterChain.doFilter(request, response);
  }
}
