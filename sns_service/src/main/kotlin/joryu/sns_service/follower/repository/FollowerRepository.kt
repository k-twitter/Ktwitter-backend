package joryu.sns_service.follower.repository

import joryu.sns_service.follower.entity.Follower
import joryu.sns_service.profile.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface FollowerRepository : JpaRepository<Follower, Long> {
    fun findAllByFollowerProfileId(followerProfileId: Long): MutableList<Follower>
    fun deleteByFollowingProfileAndFollowerProfileId(profile: Profile, followerProfileId: Long)
}