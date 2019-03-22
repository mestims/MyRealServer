package br.com.server.gateway.security

import org.springframework.beans.factory.annotation.Value

class JwtTemplate {
    @Value("\${security.jwt.uri:/auth/**}")
    lateinit var uri: String

    @Value("\${security.jwt.header:Authorization}")
    lateinit var header: String

    @Value("\${security.jwt.prefix:Bearer }")
    lateinit var prefix: String

    @Value("\${security.jwt.expiration:#{5*60*1000}}")
    var expiration: Int = -1

    @Value("\${security.jwt.secret:JwtSecretKey}")
    lateinit var secret: String

}