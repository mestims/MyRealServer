package br.com.server.auth

import br.com.server.auth.model.AuthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @RequestMapping("/auth")
    fun auth(): ResponseEntity<AuthResponse> = authOk("AuthController....")

}