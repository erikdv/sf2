package net.eriknet.sf2.security.controller

data class AuthenticationContainer(
    val accessToken: String,
    val refreshToken: String,
    val username: String,
    val sessionExpirationTime: Long
)
