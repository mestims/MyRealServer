package br.com.server.auth.security

import br.com.server.auth.security.model.AppUser
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
    private lateinit var users: List<AppUser>

    override fun loadUserByUsername(username: String?): UserDetails {

        for (user in users) {
            if (user.username == username) {
                val grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_${user.role}")
                return User(user.username, user.password, grantedAuthorities)
            }
        }
        throw UsernameNotFoundException("Username: $username not found")
    }

}