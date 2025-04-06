package net.eriknet.sf2.message.controller

import java.time.Instant

data class MessageResponse(
    val id: String,
    val title: String,
    val content: String,
    val author: String?,
    val createdAt: Instant?
)
