package joryu.sns_service.chat.service

import joryu.sns_service.chat.dto.ChatMessage
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

@Service
class ChatPublisher(
    private val channelTopic: ChannelTopic,
    private val redisTemplate: RedisTemplate<String, Any>,
) {
    fun publish(message: ChatMessage) {
        redisTemplate.convertAndSend(channelTopic.topic, message)
    }
}