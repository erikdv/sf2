package net.eriknet.sf2.comment.controller

import java.time.Instant

data class CommentResponse(
    val content: String,
    val author: String?,
    val createdAt: Instant?
)
