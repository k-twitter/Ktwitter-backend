package joryu.sns_service.chat.dto.response

import joryu.sns_service.chat.entity.ChatMessage

class ChatMessageAllResponse(chatMessages: List<ChatMessage?>) {
    val chatMessages = chatMessages.map { m -> m?.toDto() }.toList()

    private fun ChatMessage.toDto(): ChatMessageResponse {
        return ChatMessageResponse(
            this.id,
            this.message,
            1L,
            "user",
            this.postDateTime
        )
    }
}