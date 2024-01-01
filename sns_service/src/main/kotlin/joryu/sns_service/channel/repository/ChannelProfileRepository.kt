package joryu.sns_service.channel.repository

import joryu.sns_service.channel.entity.ChannelProfile
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelProfileRepository: JpaRepository<ChannelProfile, Long> {
}