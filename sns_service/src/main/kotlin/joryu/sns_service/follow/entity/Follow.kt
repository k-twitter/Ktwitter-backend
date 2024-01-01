package joryu.sns_service.follow.entity

import jakarta.persistence.*
import joryu.sns_service.common.entity.BaseEntity
import joryu.sns_service.profile.entity.Profile

@Table(name = "follow")
@Entity
class Follow(
        @Id
        @Column(name = "follow_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @ManyToOne
        @JoinColumn
        val fromProfile: Profile?,

        @ManyToOne
        @JoinColumn
        val toProfile: Profile?
) : BaseEntity() {
    constructor() : this(null, null, null)
    constructor(fromProfile: Profile, toProfile: Profile) : this(null, fromProfile, toProfile) {
        toProfile.addFollower(this)
        fromProfile.addFollowing(this)
    }
}