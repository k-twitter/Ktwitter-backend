package joryu.sns_service.chat.controller

import joryu.sns_service.chat.dto.ChatMessage
import joryu.sns_service.chat.service.ChatPublisher
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class ChatMessageController(
    private val chatPublisher: ChatPublisher
) {

    /**
     * /pub/chat/message 로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    fun sendMessage(message: ChatMessage) {

        chatPublisher.publish(message)
    }
}