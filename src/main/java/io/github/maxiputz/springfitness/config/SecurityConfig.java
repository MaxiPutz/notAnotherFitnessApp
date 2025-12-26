package io.github.maxiputz.springfitness.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private JwtAthFilter jwtAthFilter;

    @Autowired
    public void jwtAthFilter(JwtAthFilter jwtAthFilter) {
        this.jwtAthFilter = jwtAthFilter;
    }

    private UserDetailsService userDetailsService;

    @Autowired
    public void userDetailsService(UserAccessController userDetailController) {
        this.userDetailsService = userDetailController;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).csrf().disable()
                .authorizeHttpRequests(conf -> conf
                .requestMatchers("/api/login", "/api/userCreate", "/**", "/OAuthFlow**", "/app/runningApp**", "/app/RideApp**", "/app" )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class)
                                )

        // .httpBasic()
        ;

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        // return new BCryptPasswordEncoder();
    }

}

