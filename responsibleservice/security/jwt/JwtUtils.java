package cl.ckrchile.responsibleservice.security.jwt;

import java.security.Key;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${ckr.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ckr.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String getUserNameFromJwtToken(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }


    public String getUserFromJwtTokenRequest(HttpServletRequest request) {
        String token = getTokenFromHeader(request);
        return (String) Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().get("user");
    }

    public Long getUserIDFromJwtTokenRequest(HttpServletRequest request) {
        String token = getTokenFromHeader(request);
        try{
            Object user_id=Jwts.parserBuilder().setSigningKey(key()).build()
                    .parseClaimsJws(token).getBody().get("user_id");
            return Long.valueOf(String.valueOf(user_id));
        }catch(Exception ex){
            return null;
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getTokenFromHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }




}
