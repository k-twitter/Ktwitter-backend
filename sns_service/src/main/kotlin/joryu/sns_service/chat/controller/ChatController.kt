package joryu.sns_service.chat.controller

import joryu.sns_service.chat.dto.request.NewMessageRequest
import joryu.sns_service.chat.dto.response.ChatMessageAllResponse
import joryu.sns_service.chat.service.ChatService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
@RequiredArgsConstructor
class ChatController(
    val chatService: ChatService
) {
    @PostMapping("/api/chat")
    fun createMessage(@RequestBody newMessage: NewMessageRequest): ResponseEntity<Any> {
        chatService.create(newMessage)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/api/chat")
    fun getAllMessage(): ResponseEntity<ChatMessageAllResponse> {
        val findAll = chatService.findAll()
        return ResponseEntity.ok(ChatMessageAllResponse(findAll))
    }
}