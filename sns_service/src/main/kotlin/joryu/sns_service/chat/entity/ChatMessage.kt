package joryu.sns_service.chat.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.joda.time.DateTime
import java.util.*

@Table(name = "chat_message")
@Entity
class ChatMessage(
    val message: String = "",
    val profileId: Long? = null,

    @Id
    val id: String = UUID.randomUUID().toString(),
    val postDateTime: String = DateTime.now().toString()
) {
}
