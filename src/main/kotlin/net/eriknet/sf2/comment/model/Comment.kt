package net.eriknet.sf2.comment.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.Instant
import java.util.*

@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val uuid: UUID = UUID.randomUUID(),
    val messageId: String,
    val content: String,
    val author: String,
    val createdAt: Instant
)
