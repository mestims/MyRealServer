package br.com.server.auth

import br.com.server.auth.model.AuthResponse
import org.springframework.http.ResponseEntity

fun authOk(text:String): ResponseEntity<AuthResponse> {
    return ResponseEntity.ok(AuthResponse(200, text))
}