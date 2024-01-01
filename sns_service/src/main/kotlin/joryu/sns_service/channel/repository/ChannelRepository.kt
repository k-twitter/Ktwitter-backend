package joryu.sns_service.channel.repository

import joryu.sns_service.channel.entity.Channel
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelRepository: JpaRepository<Channel, String> {
}