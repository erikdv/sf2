package net.eriknet.sf2.message.controller

import net.eriknet.sf2.message.model.Message
import net.eriknet.sf2.message.service.MessageService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping("/api/messages")
    fun listAll(): List<MessageResponse> =
        messageService.findAll()
            .map { it.toResponse() }

    @PostMapping("/api/message")
    fun post(@RequestBody request: NewMessageRequest) {
        val user = SecurityContextHolder.getContext().getAuthentication().name
        messageService.save(request, user)
    }

    fun Message.toResponse(): MessageResponse =
        MessageResponse(
            title = this.title,
            content = this.content
        )

}
