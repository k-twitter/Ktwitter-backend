package joryu.sns_service.chat.dto

data class ChatMessage(
    val channelId: String,
    val senderProfileId: String,
    val content: String = "",
)
