package net.eriknet.sf2.account.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class Role(
    val name: String
) {
    @Id
    @GeneratedValue(generator = "UUID")
    var id: UUID? = null
}
