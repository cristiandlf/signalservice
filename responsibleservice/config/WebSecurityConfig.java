package cl.ckrchile.responsibleservice.config;


import cl.ckrchile.responsibleservice.enums.Roles;
import cl.ckrchile.responsibleservice.security.jwt.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public JwtTokenFilter authenticationJwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST,"/api/v1/responsible/**").hasAuthority(Roles.R_RESPONSIBLE_MANAGER.getAuthority())
                            .requestMatchers(HttpMethod.PUT,"/api/v1/responsible/**").hasAuthority(Roles.R_RESPONSIBLE_MANAGER.getAuthority())
                            .requestMatchers(HttpMethod.GET,"/api/v1/responsible/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/v1/responsible/all").hasAuthority(Roles.R_RESPONSIBLE_MANAGER.getAuthority())
                            .requestMatchers(HttpMethod.GET,"/api/v1/responsible/page").hasAuthority(Roles.R_RESPONSIBLE_MANAGER.getAuthority())
                            .anyRequest().authenticated()
                );

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
