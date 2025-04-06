package net.eriknet.sf2.security.config

import net.eriknet.sf2.security.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(
        http:HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth", "/api/auth/refresh")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/account")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/account**")
                    .fullyAuthenticated()
                    .requestMatchers(HttpMethod.GET, "/api/comments")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/messages")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/message")
                    .fullyAuthenticated()
                    .requestMatchers(HttpMethod.POST, "/api/category")
                    .fullyAuthenticated()
                    .requestMatchers(HttpMethod.GET, "/api/category")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/error")
                    .permitAll()
                    .requestMatchers("/v3/api-docs")
                    .permitAll()
                    .requestMatchers("/v3/api-docs/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui/**")
                    .permitAll()
            }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider((authenticationProvider))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}
