package net.eriknet.sf2.message.repository

import net.eriknet.sf2.message.model.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageRepository : CrudRepository<Message, UUID> {
    override fun findAll(): List<Message>
    fun findByCategory(slug: String): List<Message>

}
