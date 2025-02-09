package net.eriknet.sf2.message.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import net.eriknet.sf2.category.model.Category
import java.time.Instant
import java.util.*


@Entity
class Message(
    val title: String,
    val content: String,
    val author: String,
    val createdAt: Instant,
    @ManyToMany
    @JoinTable(
        name = "message_category",
        joinColumns = [JoinColumn(name = "message_uuid")],
        inverseJoinColumns = [JoinColumn(name = "category_uuid")]
    )
    val categories: MutableSet<Category> = mutableSetOf()


) {
    @Id
    @GeneratedValue(generator = "UUID")
    var uuid: UUID? = null
}

