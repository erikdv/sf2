package net.eriknet.sf2.message.controller

import net.eriknet.sf2.message.model.Message
import net.eriknet.sf2.message.service.MessageService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageService: MessageService
) {

    @GetMapping("/api/messages")
    fun getMessages(@RequestParam slug: String?): List<MessageResponse> {
        return if (slug == null) {
            messageService.findAll()
                .map { it.toResponse() }
        } else {
            messageService.findByCategory(slug)
                .map { it.toResponse() }
        }
    }

    @PostMapping("/api/message")
    fun post(@RequestBody request: NewMessageRequest) {
        val user = SecurityContextHolder.getContext().getAuthentication().name
        messageService.save(request, user)
    }

    fun Message.toResponse(): MessageResponse =
        MessageResponse(
            title = this.title,
            content = this.content,
            author = this.author,
            createdAt = this.createdAt
        )

}
