package net.eriknet.sf2.security.controller

data class AuthenticationRequest(
    val username: String,
    val password: String
)
