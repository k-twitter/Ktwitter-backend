package joryu.sns_service.channel.entity

import jakarta.persistence.*
import joryu.sns_service.channel.enums.ChannelType
import java.io.Serializable
import java.util.*


@Table(name = "channel")
@Entity
class Channel(
    channelName: String = "",

    @Column(name = "channel_type")
    val channelType: ChannelType = ChannelType.PERSONAL
) : Serializable {
    @Id
    @Column(name = "channel_id")
    val id: String = UUID.randomUUID().toString()

    @OneToMany(mappedBy = "channel")
    val channelProfiles: MutableList<ChannelProfile> = mutableListOf()

    @Column(name = "channel_name")
    var channelName: String = channelName
        private set

    fun updateChannelName(newName: String) {
        channelName = newName
    }

    fun addProfileToChannel(profile: ChannelProfile) {
        channelProfiles.add(profile)
    }
}