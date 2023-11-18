package joryu.sns_service.channel.entity

import jakarta.persistence.*
import joryu.sns_service.profile.entity.Profile
import java.io.Serializable

@Table(name = "channel_profile")
@Entity
class ChannelProfile(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    val channel: Channel = Channel(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    val profile: Profile = Profile()
): Serializable {
    @Id
    @Column(name = "channel_profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
