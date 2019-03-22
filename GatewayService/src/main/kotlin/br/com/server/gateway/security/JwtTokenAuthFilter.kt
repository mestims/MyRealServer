package br.com.server.gateway.security

import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenAuthFilter(private val jwtTemplate: JwtTemplate) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val header = request.getHeader(jwtTemplate.header)
        if (header != null && header.startsWith(jwtTemplate.prefix)) {

            val token = header.replace(jwtTemplate.prefix, "")
            try {
                val claims = Jwts.parser()
                    .setSigningKey(jwtTemplate.secret.toByteArray())
                    .parseClaimsJws(token)
                    .body

                val username = claims.subject
                username?.let {
                    val authorities = claims["authorities"] as? List<String>
                    val auth = UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities?.map { SimpleGrantedAuthority(it) })
                    SecurityContextHolder.getContext().authentication = auth
                }
            } catch (e: Exception) {
                SecurityContextHolder.clearContext()
            }
        }
        filterChain.doFilter(request, response)
    }
}