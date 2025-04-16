package net.eriknet.sf2.comment.service

import net.eriknet.sf2.comment.controller.NewCommentRequest
import net.eriknet.sf2.comment.model.Comment
import net.eriknet.sf2.comment.repository.CommentRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CommentService(
    private val repository: CommentRepository
) {
    fun findByMessageId(messageId: String): List<Comment> =
        repository.findByMessageId(messageId)

    fun save(comment: NewCommentRequest, author: String) {
        repository.save(comment.toComment(author))
    }

    private fun NewCommentRequest.toComment(author: String): Comment =
        Comment(
            messageId = this.messageId,
            content = this.content,
            author = author,
            createdAt = Instant.now()
        )
}
