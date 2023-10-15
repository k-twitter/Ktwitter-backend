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
    val id: String = UUID.randomUUID().toString()

    @OneToMany(mappedBy = "channel")
    val channelUsers: MutableList<ChannelProfile> = mutableListOf()

    @Column(name = "channel_name")
    var channelName: String = channelName
        private set

    fun changeChannelName(newName: String) {
        channelName = newName
    }

    fun addUserToChannel(user: ChannelProfile) {
        channelUsers.add(user)
    }
}