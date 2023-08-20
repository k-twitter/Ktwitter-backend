package joryu.sns_service.chat.repository

import joryu.sns_service.chat.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository : JpaRepository<ChatMessage, Long> {

}