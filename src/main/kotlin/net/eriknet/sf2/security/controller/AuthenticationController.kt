package net.eriknet.sf2.security.controller

import io.jsonwebtoken.MalformedJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.eriknet.sf2.security.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authService: AuthenticationService,
) {

    @PostMapping
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest,
        response: HttpServletResponse
    ) : AuthenticationResponse {

        val authContainer = authService.authenticate(authRequest)
        response.addCookie(authService.getAccessTokenCookie(authContainer))
        response.addCookie(authService.getRefreshTokenCookie(authContainer))

        return AuthenticationResponse(authContainer.username, authContainer.sessionExpirationTime)
    }


    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(ex: BadCredentialsException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.UNAUTHORIZED
        val errorResponse = ErrorResponse(
            message = "Invalid credentials",
        )
        return ResponseEntity.status(status).body(errorResponse)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(request: HttpServletRequest, response: HttpServletResponse): AuthenticationResponse? {

        val refreshToken = extractTokenFromCookies(request, "refreshToken") ?: return null

        val authContainer =  authService.refreshAccessToken(refreshToken)

        response.addCookie(authService.getAccessTokenCookie(authContainer))
        response.addCookie(authService.getRefreshTokenCookie(authContainer))

        return AuthenticationResponse(authContainer.username, authContainer.sessionExpirationTime)

    }

    private fun extractTokenFromCookies(request: HttpServletRequest, cookieKey: String): String? {
        val cookies = request.cookies ?: return null
        return cookies.find { it.name == cookieKey }?.value
    }

    @ExceptionHandler(MalformedJwtException::class)
    fun handleMalformedJwt(ex: MalformedJwtException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        val errorResponse = ErrorResponse(
            message = "Invalid JWT string",
        )
        return ResponseEntity.status(status).body(errorResponse)
    }

    fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(accessToken = this)

}
