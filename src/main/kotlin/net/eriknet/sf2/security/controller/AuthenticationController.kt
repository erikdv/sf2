package net.eriknet.sf2.security.controller

import io.jsonwebtoken.MalformedJwtException
import net.eriknet.sf2.security.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.CrossOrigin
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
    private val authenticationService: AuthenticationService
) {

    @CrossOrigin("http://localhost:3000")
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse =
            authenticationService.authenticate(authRequest)

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(ex: BadCredentialsException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.UNAUTHORIZED
        val errorResponse = ErrorResponse(
            message = "Invalid credentials",
        )
        return ResponseEntity.status(status).body(errorResponse)
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody request: RefreshTokenRequest): TokenResponse =
        authenticationService.refreshAccessToken(request.refreshToken)
        ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token")

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
