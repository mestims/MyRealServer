package br.com.server.auth.security

import br.com.server.auth.UserRepository
import br.com.server.auth.security.model.JwtTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfig {


    @Bean
    fun providesJwtTemplate(): JwtTemplate = JwtTemplate()

    @Bean
    fun providesPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun providesUserRepository(encoder: BCryptPasswordEncoder): UserRepository = UserRepository(encoder)
}