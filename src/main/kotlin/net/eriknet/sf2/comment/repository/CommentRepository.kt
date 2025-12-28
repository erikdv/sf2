package net.eriknet.sf2.comment.repository

import net.eriknet.sf2.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CommentRepository : JpaRepository<Comment, UUID> {
    fun findByMessageId(messageId: String): List<Comment>
    fun countByMessageId(messageId: String): Long
}
