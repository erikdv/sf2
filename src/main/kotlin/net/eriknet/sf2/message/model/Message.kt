package net.eriknet.sf2.message.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.Instant
import java.util.*


@Entity
class Message(
    val title: String,
    val content: String,
    val author: String,
    val createdAt: Instant
) {
    @Id
    @GeneratedValue(generator = "UUID")
    var uuid: UUID? = null
}

