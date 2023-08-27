package joryu.sns_service.follower.entity

import jakarta.persistence.*
import joryu.sns_service.profile.entity.Profile

@Table(name = "follower")
@Entity
data class Follower(
        @Id
        @Column(name = "follower_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @ManyToOne
        @JoinColumn(name = "profile_id")
        val followingProfile: Profile?,

        @Column(name = "follower_profile_id")
        val followerProfileId: Long?
) {
    constructor() : this(null, null, null)
    constructor(followingProfile: Profile, followerProfileId: Long) : this(null, followingProfile, followerProfileId) {
        followingProfile.addFollower(this)
    }
}