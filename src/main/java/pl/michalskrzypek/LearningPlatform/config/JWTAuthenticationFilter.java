package pl.michalskrzypek.LearningPlatform.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.michalskrzypek.LearningPlatform.entities.User;
import pl.michalskrzypek.LearningPlatform.utils.JWTTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class checks for the authorization header and authenticates the JWT token and sets the authentication in the context.
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Value("${HEADER_STRING}")
    private String headerString;
    @Value("${TOKEN_PREFIX}")
    private String tokenPrefix;

    private JWTTokenUtil JWTTokenUtil;

    public JWTAuthenticationFilter(JWTTokenUtil JWTTokenUtil) {
        this.JWTTokenUtil = JWTTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(headerString);
        String authToken;

        if (header != null && header.startsWith(tokenPrefix)) {
            authToken = header.replace(tokenPrefix, "");
            try {
                setAuthentication(authToken, req);
            } catch (IllegalArgumentException e) {
                logger.error("An error occurred during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("This token has expired and is not longer valid", e);
            }
        } else {
            logger.warn("There is no JWT put in the request header!");
        }
        chain.doFilter(req, res);
    }

    private void setAuthentication(String authToken, HttpServletRequest req) {
        if (SecurityContextHolder.getContext().getAuthentication() == null && JWTTokenUtil.validateToken(authToken)) {
            User user = JWTTokenUtil.getUserFromToken(authToken);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            logger.info("Authenticated user " + user.getUsername() + ", setting security context");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
