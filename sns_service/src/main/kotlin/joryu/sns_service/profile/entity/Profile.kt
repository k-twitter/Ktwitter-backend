package joryu.sns_service.profile.entity

import jakarta.persistence.*
import joryu.sns_service.channel.entity.Channel
import joryu.sns_service.channel.entity.ChannelProfile
import joryu.sns_service.common.entity.BaseEntity
import joryu.sns_service.follow.entity.Follow
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest


@Table(name = "profile")
@Entity
class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "profile_id")
        val id: Long,
        @Column(name = "name")
        var name: String,
        @Column(name = "follower_number")
        var followerNumber: Int,
        @Column(name = "following_number")
        var followingNumber: Int,


        @OneToMany(mappedBy = "fromProfile")
        val followers: MutableList<Follow> = mutableListOf(),
        @OneToMany(mappedBy = "toProfile")
        val followings: MutableList<Follow> = mutableListOf()
) : BaseEntity() {
    constructor() : this(0, "", 0, 0)
    constructor(name: String) : this(0, name, 0, 0)

    @OneToMany(mappedBy = "profile")
    val channelProfiles: MutableList<ChannelProfile> = mutableListOf()

    fun update(profileUpdateRequest: ProfileUpdateRequest) {
        this.name = profileUpdateRequest.name
    }
    fun addFollower(follow: Follow) {
        this.followerNumber++
    }

    fun addFollowing(following: Follow) {
        this.followingNumber++
    }

    fun addChannel(channel: Channel) {
        channelProfiles.add(ChannelProfile(channel, this))
    }
}

