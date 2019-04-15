package br.com.server.auth.model

import br.com.server.auth.security.model.AppUser

data class UserServiceResponse(
    val error: Any?,
    val data: List<AppUser>
)