package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  private final String jwtSecretKey;

  public JwtServiceImpl(@Value("${jwt.secret}") String jwtSecretKey) {
    this.jwtSecretKey = jwtSecretKey;
  }

  // decode

  public UserDetails extractUserInformation(String token) {
    Claims allClaims = extractAllClaims(token);

    String userName = extractUserName(allClaims);

    List<String> allRoles = extractUserRoles(allClaims);

    return new User(userName, "", allRoles.stream().map(a -> new SimpleGrantedAuthority("ROLE_" + a)).toList());

  }

  @SuppressWarnings("unchecked")
  private static List<String> extractUserRoles(Claims allClaims) {
    return (List<String>)allClaims.get("roles", List.class);
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
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
