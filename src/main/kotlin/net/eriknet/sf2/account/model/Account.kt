package net.eriknet.sf2.account.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.*

@Entity
class Account(
    val username: String,
    val password: String,
    @ElementCollection(targetClass = Role::class)
    val roles: List<Role> = listOf(Role.USER)
) {
    @Id
    @GeneratedValue(generator = "UUID")
    var id: UUID? = null
}


