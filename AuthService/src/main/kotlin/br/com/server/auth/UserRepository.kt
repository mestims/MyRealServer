package br.com.server.auth

import br.com.server.auth.model.UserServiceResponse
import br.com.server.auth.security.model.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.RequestEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class UserRepository(val encoder: BCryptPasswordEncoder) {

    @Autowired
    lateinit var restClient: RestTemplate

    @Autowired
    @Qualifier("users-request")
    lateinit var requestEntity: RequestEntity<Void>

    companion object {
        private const val USER = "USER"
        private const val ADMIN = "ADMIN"
    }

    fun getUsers(): List<AppUser>? {
        val response = restClient.exchange(requestEntity, UserServiceResponse::class.java)

        return if (response.statusCode.is2xxSuccessful) {
            response.body?.data?.map { AppUser(it.id, it.username, encoder.encode(it.password), it.role) }
        } else {
            null
        }
    }

    fun getMockUsers(): List<AppUser> = listOf(
        AppUser(1, "admin", encoder.encode("12345"), ADMIN),
        AppUser(2, "matheus", encoder.encode("12345"), USER)
    )
}