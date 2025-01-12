package net.eriknet.sf2.message.controller

data class NewMessageRequest(
    val title: String,
    val content: String,
    val category: String
)
