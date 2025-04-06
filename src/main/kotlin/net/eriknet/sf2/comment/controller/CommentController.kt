package net.eriknet.sf2.comment.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController()
class CommentController {

    @GetMapping("/api/comments")
    fun getComments(@RequestParam messageId: String) : List<CommentResponse> {
        return listOf(
            CommentResponse("hi", "erik", Instant.now()),
            CommentResponse("hi2", "erik", Instant.now()),
            CommentResponse("hi3", "erik", Instant.now()),
        )
    }
}
