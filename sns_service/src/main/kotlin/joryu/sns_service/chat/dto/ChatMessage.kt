package joryu.sns_service.chat.dto

data class ChatMessage(
    val channelId: String,
    val sender: String,
    val content: String = "",
)
