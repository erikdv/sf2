package net.eriknet.sf2.security.controller

data class AuthenticationResponse(
    val username: String,
    val sessionExpirationTime: Long,
    val sessionRefreshExpirationTime: Long
)
