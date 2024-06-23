package net.eriknet.sf2.security.service

import net.eriknet.sf2.account.repository.AccountRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

typealias ApplicationUser = net.eriknet.sf2.account.model.Account
@Service
class CustomUserDetailsService(
    private val userRepository: AccountRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Not found")

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.username)
            .password(this.password)
            .roles(*(this.roles.map { it.name }.toTypedArray()))
            .build()
}
