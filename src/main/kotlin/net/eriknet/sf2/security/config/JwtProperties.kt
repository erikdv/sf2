package net.eriknet.sf2.security.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Int,
    val refreshTokenExpiration: Int
)
