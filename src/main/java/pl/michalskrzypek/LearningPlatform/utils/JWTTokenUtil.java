package pl.michalskrzypek.LearningPlatform.utils;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.entity.User;
import io.jsonwebtoken.Claims;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

/**
 * Following is the util class to generate the auth token as well as to extract username from the token.
 */
@Component
public class JWTTokenUtil implements Serializable {

    @Value("${SIGNING_KEY}")
    private String signingKey;

    @Value("${ACCESS_TOKEN_VALIDITY_SECONDS}")
    private int accessTokenValiditySeconds;

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://michalskrzypek.la.pl")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValiditySeconds*1000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.ES256, signingKey)
                .compact();
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token));
    }

}
