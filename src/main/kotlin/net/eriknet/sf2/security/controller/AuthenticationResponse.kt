package net.eriknet.sf2.security.controller

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
