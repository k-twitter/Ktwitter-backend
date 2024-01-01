package joryu.sns_service.chat.service

import com.fasterxml.jackson.databind.ObjectMapper
import joryu.sns_service.chat.dto.ChatMessage
import mu.KLogger
import mu.KotlinLogging
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class ChatListener(
    private val objectMapper: ObjectMapper,
    private val redisTemplate: RedisTemplate<String, Any>,
    private val messagingTemplate: SimpMessageSendingOperations,
): MessageListener {
    val log: KLogger = KotlinLogging.logger {}

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val publishMessage = redisTemplate.stringSerializer.deserialize(message.body)
            val chatMessage = objectMapper.readValue(publishMessage, ChatMessage::class.java)

            messagingTemplate.convertAndSend("/sub/chat/channel/" + chatMessage.channelId, chatMessage)
        } catch (e: Exception) {
            log.error { e.stackTraceToString() }
        }
    }
}