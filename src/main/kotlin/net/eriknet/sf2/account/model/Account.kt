package net.eriknet.sf2.account.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.util.*

@Entity
class Account(
    val username: String,
    val password: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_roles",
        joinColumns = [JoinColumn(name = "account_id")],
        inverseJoinColumns = [JoinColumn(name = "role")]
    )
    val roles: Set<Role> = setOf()
) {
    @Id
    @GeneratedValue(generator = "UUID")
    var id: UUID? = null
}


