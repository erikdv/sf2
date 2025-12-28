package net.eriknet.sf2.comment.controller

import java.time.Instant

data class CommentCountResponse(
    val messageId: String,
    val count: Long
)
