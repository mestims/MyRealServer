package br.com.server.auth.security

import br.com.server.auth.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {

        for (user in userRepository.getUsers()) {
            if (user.username == username) {
                val grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_${user.role}")
                return User(user.username, user.password, grantedAuthorities)
            }
        }
        throw UsernameNotFoundException("Username: $username not found")
    }

}