package br.com.server.auth

import br.com.server.auth.security.model.AppUser
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepository(val encoder: BCryptPasswordEncoder) {

    companion object {
        private const val USER = "USER"
        private const val ADMIN = "ADMIN"
    }

    fun getUsers(): List<AppUser> = listOf(
        AppUser(1, "admin", encoder.encode("12345"), ADMIN),
        AppUser(2, "matheus", encoder.encode("12345"), USER)
    )
}