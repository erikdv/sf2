package net.eriknet.sf2.security.service

import jakarta.servlet.http.Cookie
import net.eriknet.sf2.security.config.JwtProperties
import net.eriknet.sf2.security.controller.AuthenticationRequest
import net.eriknet.sf2.security.controller.AuthenticationContainer
import net.eriknet.sf2.security.model.RefreshToken
import net.eriknet.sf2.security.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationContainer {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val currentTime = System.currentTimeMillis()

        val accessToken = generateAccessToken(user, currentTime)

        val refreshToken = generateRefreshToken(user, currentTime)

        refreshTokenRepository.save(RefreshToken( refreshToken, user.username))

        return AuthenticationContainer(
            accessToken,
            refreshToken,
            user.username,
            currentTime + jwtProperties.accessTokenExpiration
        )
    }

    fun getAccessTokenCookie(authContainer: AuthenticationContainer): Cookie {
        return Cookie("accessToken", authContainer.accessToken).apply {
            isHttpOnly = true
            secure = true
            path = "/"
            maxAge = 10
        }
    }

    fun getRefreshTokenCookie(authContainer: AuthenticationContainer): Cookie {
        return Cookie("refreshToken", authContainer.refreshToken).apply {
            isHttpOnly = true
            secure = true
            path = "/"
            maxAge = 7 * 24 * 60 * 60 // 7 days
        }
    }

    private fun generateRefreshToken(user: UserDetails, currentTime: Long) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(currentTime + jwtProperties.refreshTokenExpiration)
    )

    private fun generateAccessToken(user: UserDetails, currentTime: Long) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(currentTime + jwtProperties.accessTokenExpiration),
        additionalClaims = mapOf("roles" to user.authorities.map { it.authority }.toList())
    )

    fun refreshAccessToken(refreshToken: String): AuthenticationContainer {
        val extractedUsername = tokenService.extractUsername(refreshToken)
        val currentTime = System.currentTimeMillis()

        extractedUsername?.let { username ->
            val currentUserDetails = userDetailsService.loadUserByUsername(username)
            val refreshTokenUserDetails = refreshTokenRepository.findByRefreshToken(refreshToken)

            if (!tokenService.isExpired(refreshToken) && currentUserDetails.username == refreshTokenUserDetails.username) {
                val accessToken = generateAccessToken(currentUserDetails, currentTime)
                return AuthenticationContainer(
                    accessToken,
                    refreshToken,
                    username,
                    currentTime + jwtProperties.accessTokenExpiration
                )
            }
        }
        throw RuntimeException()
    }
}
