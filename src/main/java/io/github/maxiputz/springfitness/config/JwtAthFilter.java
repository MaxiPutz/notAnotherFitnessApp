package io.github.maxiputz.springfitness.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
public class JwtAthFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
 
    @Autowired
    public void userDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String userEmail;
        final String jwtToken;

        System.out.println("request url " + request.getRequestURL());

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            System.out.println("jwt not found try to login");
            
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("in the chain2");
        System.out.println(authHeader);
        System.out.println("chain 2");
        jwtToken = authHeader.substring(7);

        System.out.println(jwtToken);
        userEmail = jwtUtils.extractUsername(jwtToken);
        System.out.println(userEmail);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            final boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails);

            if (isTokenValid) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                System.out.println("token is valid");

                System.out.println(userDetails.getAuthorities().size());
                System.out.println(userDetails.getAuthorities().stream().findFirst().get());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("end of valid");

            }
        }

        filterChain.doFilter(request, response);

    }

}
