package net.eriknet.sf2.category.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import net.eriknet.sf2.message.model.Message
import java.util.*

@Entity
class Category(
    val slug: String,
    val title: String,
    @Column(name = "\"order_\"")
    val order: Int
) {
    @Id
    @GeneratedValue(generator = "UUID")
    var uuid: UUID? = null

    @ManyToMany(mappedBy = "categories")
    val messages: MutableSet<Message> = mutableSetOf()
}
