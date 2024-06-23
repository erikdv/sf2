package net.eriknet.sf2.security.config

import net.eriknet.sf2.security.service.CustomUserDetailsService
import net.eriknet.sf2.account.repository.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class Configuration(

) {
    @Bean
    fun userDetailsService(accountRepository: AccountRepository) : UserDetailsService=
        CustomUserDetailsService(accountRepository)

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(accountRepository: AccountRepository): AuthenticationProvider=
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(accountRepository))
                it.setPasswordEncoder(encoder())
            }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}
