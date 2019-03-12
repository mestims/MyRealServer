package br.com.server.auth.secutiry;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JwtTokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String POST = "POST";
    private final JwtConfig jwtConfig;
    private AuthenticationManager authenticationManager;


    public JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;

        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), POST));
    }




}
