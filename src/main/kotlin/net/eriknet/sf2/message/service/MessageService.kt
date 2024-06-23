package net.eriknet.sf2.message.service

import net.eriknet.sf2.message.model.Message
import net.eriknet.sf2.message.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val repository : MessageRepository
) {
    fun findAll(): List<Message> =
        repository.findAll()

    fun save(message: Message) {
        repository.save(message)
    }
}
