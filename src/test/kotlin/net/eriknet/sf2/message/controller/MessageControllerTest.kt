package net.eriknet.sf2.message.controller

import net.eriknet.sf2.message.model.Message
import net.eriknet.sf2.message.service.MessageService
import net.eriknet.sf2.security.service.TokenService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.Instant


@WebMvcTest(MessageController::class)
@WithMockUser(roles = ["USER"])
class MessageControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var messageService: MessageService

    @MockBean
    lateinit var tokenService: TokenService

    @Test
    fun `listAll should return all messages`() {
        // given
        val messages = listOf( Message(title = "title", content = "content", author = "test", createdAt = Instant.now()))
        `when`(messageService.findAll()).thenReturn(messages)

        // when
        val result = this.mockMvc.perform(get("/api/messages"))

        // then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$[0].title").value("title"))
            .andExpect(jsonPath("$[0].content").value("content"))
    }
}
