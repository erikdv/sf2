package net.eriknet.sf2.comment.controller

import net.eriknet.sf2.comment.service.CommentService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping("/api/comments")
    fun getComments(@RequestParam messageId: String): List<CommentResponse> {
        return commentService.findByMessageId(messageId)
            .map { CommentResponse(it.content, it.author, it.createdAt) }
    }

    @PostMapping("/api/comments")
    fun postComment(@RequestBody request: NewCommentRequest) {
        val user = SecurityContextHolder.getContext().authentication.name
        commentService.save(request, user)
    }
}
