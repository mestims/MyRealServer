package br.com.server.auth.security

import br.com.server.auth.security.model.JwtTemplate
import br.com.server.auth.security.model.UserCredentials
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserCredentialsFilter(
    private val authManager: AuthenticationManager,
    private val jwtTemplate: JwtTemplate
) : UsernamePasswordAuthenticationFilter() {

    companion object {
        private const val POST = "POST"
    }

    init {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(jwtTemplate.uri, POST))
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        return try {
            val credentials = jacksonObjectMapper().readValue<UserCredentials>(request?.inputStream?.bufferedReader().use { it?.readText()!! })
            val token = UsernamePasswordAuthenticationToken(credentials.username, credentials.password, emptyList())
            authManager.authenticate(token)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?
    ) {
        val currentTime = System.currentTimeMillis()
        val token = Jwts.builder()
            .setSubject(authResult?.name)
            .claim("authorities", authResult?.authorities?.map { it.authority })
            .setIssuedAt(Date(currentTime))
            .setExpiration(Date(currentTime + jwtTemplate.expiration))
            .signWith(SignatureAlgorithm.HS512, jwtTemplate.secret.toByteArray())
            .compact()

        response?.addHeader(jwtTemplate.header, jwtTemplate.prefix + token)
    }
}