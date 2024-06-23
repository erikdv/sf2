package net.eriknet.sf2.account.controller

import java.util.*

data class AccountResponse(
    val username: String,
    val uuid: UUID?,
    val roles: List<String>
)
