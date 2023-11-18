package joryu.sns_service.channel.entity

import jakarta.persistence.*
import joryu.sns_service.profile.entity.Profile
import java.io.Serializable

@Table(name = "channel_profile")
@Entity
class ChannelProfile(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    var channel: Channel? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    var profile: Profile? = null
): Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
