package br.com.server.auth.security

import br.com.server.auth.security.model.JwtTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED
import javax.ws.rs.HttpMethod.POST

@EnableWebSecurity
class SecurityCredentialsAdapter : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userService: UserDetailsService

    @Autowired
    private lateinit var jwtTemplate: JwtTemplate

    @Autowired
    private lateinit var encoder: BCryptPasswordEncoder

    override fun configure(http: HttpSecurity?) {
        http?.run {
            this.csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint { _, response, _ -> response.sendError(SC_UNAUTHORIZED) }
                .and()
                .addFilter(UserCredentialsFilter(authenticationManager(),jwtTemplate))
                .authorizeRequests()
                .antMatchers(POST, jwtTemplate.uri).permitAll()
                .anyRequest().authenticated()

        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userService)?.passwordEncoder(encoder)
    }
}