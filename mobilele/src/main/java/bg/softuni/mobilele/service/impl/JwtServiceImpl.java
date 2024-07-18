package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
  private final String jwtSecretKey;
  private final long jwtExpirationMs;

  public JwtServiceImpl(
      @Value("${jwt.secret}") String jwtSecretKey,
      @Value("${jwt.expiration}") long jwtExpirationMs) {
    this.jwtSecretKey = jwtSecretKey;
    this.jwtExpirationMs = jwtExpirationMs;
  }

  public String generateToken(String userId, Map<String, Object> claims) {

    var now = new Date(System.currentTimeMillis());

    return Jwts
        .builder()
        .setClaims(claims)
        .setSubject(userId)
        .setIssuedAt(now)
        .setNotBefore(now)
        .setExpiration(new Date(now.getTime() + jwtExpirationMs))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
