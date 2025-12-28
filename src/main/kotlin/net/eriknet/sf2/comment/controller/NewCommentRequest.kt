package net.eriknet.sf2.comment.controller

data class NewCommentRequest(
    val messageId: String,
    val content: String
)
