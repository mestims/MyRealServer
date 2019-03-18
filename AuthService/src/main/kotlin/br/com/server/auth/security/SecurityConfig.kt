package br.com.server.auth.security

import br.com.server.auth.security.model.AppUser
import br.com.server.auth.security.model.JwtTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfig {

    companion object {
        private const val USER = "USER"
        private const val ADMIN = "ADMIN"
    }

    @Bean
    fun providesJwtTemplate():JwtTemplate = JwtTemplate()

    @Bean
    fun providesPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun providesAppUserList(encoder: BCryptPasswordEncoder): List<AppUser> = listOf(
        AppUser(1, "admin", encoder.encode("12345"), ADMIN),
        AppUser(2, "matheus", encoder.encode("12345"), USER)
    )
}