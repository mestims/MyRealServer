package br.com.server.gateway.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig {

    @Bean
    fun providesJwtTemplate(): JwtTemplate = JwtTemplate()

    @Bean
    fun providesJwtTokenAuthFilter(jwtTemplate: JwtTemplate): JwtTokenAuthFilter = JwtTokenAuthFilter(jwtTemplate)

}