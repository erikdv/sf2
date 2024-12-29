package net.eriknet.sf2.security.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class RefreshToken(
    val refreshToken: String,
    val username: String,
) {
    @Id
    @GeneratedValue(generator = "UUID")
    var id: UUID? = null
}
