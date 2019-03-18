package br.com.server.auth.security.model

data class AppUser(
    val id: Int,
    val username: String,
    val password: String,
    val role: String
)