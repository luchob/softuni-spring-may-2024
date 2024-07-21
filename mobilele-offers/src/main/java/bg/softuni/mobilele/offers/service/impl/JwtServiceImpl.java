package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService {

  private final String jwtSecret;

  public JwtServiceImpl(@Value("${jwt.secret}") String jwtSecret) {
    this.jwtSecret = jwtSecret;
  }


  @Override
  public UserDetails extractUserInformation(String token) {

    Claims allClaims = extractAllClaims(token);
    String userName = extractUserName(allClaims);
    List<String> allRoles = extractUserRoles(allClaims);
    return new User(userName, "", allRoles.stream().map(SimpleGrantedAuthority::new).toList());

  }

  @SuppressWarnings("unchecked")
  private static List<String> extractUserRoles(Claims allClaims) {
    return extractClaim(allClaims, c -> c.get("roles", List.class));
  }
  private static String extractUserName(Claims claims) {
    return extractClaim(claims, Claims::getSubject);
  }
  private static <T> T extractClaim(Claims claims, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey() {
    byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
