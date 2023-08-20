package joryu.sns_service.chat.service

import joryu.sns_service.chat.dto.request.NewMessageRequest
import joryu.sns_service.chat.entity.ChatMessage
import joryu.sns_service.chat.repository.ChatRepository
import joryu.sns_service.profile.repository.ProfileRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ChatService(
    private val chatRepository: ChatRepository,
    private val profileRepository: ProfileRepository
){

    fun findAll(): List<ChatMessage?> {
        return chatRepository.findAll()
    }

    fun create(newMessage: NewMessageRequest) {
        val chatMessage = ChatMessage(newMessage.message, 1)
        chatRepository.save(chatMessage)
    }
}