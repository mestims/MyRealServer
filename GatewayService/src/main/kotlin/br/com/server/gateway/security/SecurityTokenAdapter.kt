package br.com.server.gateway.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED
import javax.ws.rs.HttpMethod.POST

@EnableWebSecurity
class SecurityTokenAdapter : WebSecurityConfigurerAdapter() {

    companion object {
        private const val ADMIN = "ADMIN"
    }

    @Autowired
    lateinit var jwtTemplate: JwtTemplate

    @Autowired
    lateinit var jwtTokenAuthFilter: JwtTokenAuthFilter

    override fun configure(http: HttpSecurity?) {
        http?.run {
            this
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint { _, response, _ -> response.sendError(SC_UNAUTHORIZED) }
                .and()
                .addFilterAfter(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers(POST, jwtTemplate.uri).permitAll()
                .antMatchers("/admin").hasRole(ADMIN)
                .anyRequest().authenticated()
        }
    }
}