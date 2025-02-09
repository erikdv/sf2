package net.eriknet.sf2.message.controller

import net.eriknet.sf2.category.controller.CategoryResponse
import java.time.Instant

data class MessageResponse(
    val title: String,
    val content: String,
    val author: String?,
    val createdAt: Instant?,
    val categories: Set<CategoryResponse>
)
