package pl.michalskrzypek.LearningPlatform.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.services.UserService;

import java.io.Serializable;
import java.util.Date;

/**
 * Following is the util class to generate the auth token as well as to extract username from the token.
 */
@Component
public class JWTTokenUtil implements Serializable {

    @Value("${SIGNING_KEY}")
    private String signingKey;
    @Value("${ACCESS_TOKEN_VALIDITY_SECONDS}")
    private int accessTokenValiditySeconds;

    private UserService userService;

    public JWTTokenUtil (UserService userService){
        this.userService = userService;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims();
        claims.setSubject(user.getEmail());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://michalskrzypek.learning-platform.pl")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValiditySeconds * 1000))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public Boolean validateToken(String token) {
        return (!isTokenExpired(token) && getUserFromToken(token) != null);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public User getUserFromToken(String token) {
        return (User) userService.loadUserByUsername(getUsernameFromToken(token));
    }

    private String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private String getRoleFromToken(String token) {
        return (String) getAllClaimsFromToken(token).get("role");
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
