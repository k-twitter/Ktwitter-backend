package joryu.sns_service.chat.dto.response

import org.joda.time.DateTime

class ChatMessageResponse(
    val id: String,
    val message: String?,
    val profileId: Long,
    val profileName: String,
    val postDateTime: String
){
}