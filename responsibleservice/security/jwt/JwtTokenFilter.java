package cl.ckrchile.responsibleservice.security.jwt;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collection;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.ArrayList;


public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${ckr.app.jwtSecret}")
    private String secretKey;

    @Value("${ckr.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody();
                String user = claims.getSubject();
                Collection<SimpleGrantedAuthority> authorities = extractRolesFromClaims(claims);

                if (user != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Collection<SimpleGrantedAuthority> extractRolesFromClaims(Claims claims) {
        List<String> roles = claims.get("roles", List.class);
        if (roles != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return authorities;
        }
        return new ArrayList<>();
    }
}
