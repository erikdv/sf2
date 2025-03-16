package net.eriknet.sf2.message.service

import net.eriknet.sf2.message.controller.NewMessageRequest
import net.eriknet.sf2.message.model.Message
import net.eriknet.sf2.message.repository.MessageRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class MessageService(
    private val repository : MessageRepository
) {
    fun findAll(): List<Message> =
        repository.findAll()

    fun findByCategory(slug: String): List<Message> =
        repository.findByCategory(slug)

    fun save(message: NewMessageRequest, user: String) {
        repository.save(message.toMessage(author = user))
    }

    fun NewMessageRequest.toMessage(author: String): Message =
        Message(
            title = this.title,
            content = this.content,
            author = author,
            createdAt = Instant.now(),
            category = this.category ?: DEFAULT
        )

    companion object{
        private val DEFAULT = "default"
    }

}


