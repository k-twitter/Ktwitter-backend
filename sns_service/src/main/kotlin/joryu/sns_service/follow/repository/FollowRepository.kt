package joryu.sns_service.follow.repository

import joryu.sns_service.follow.entity.Follow
import joryu.sns_service.profile.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository : JpaRepository<Follow, Long> {
    fun deleteByFromProfileAndToProfile(fromProfile: Profile, toProfile: Profile)
    fun findAllByToProfile(toProfile: Profile): MutableList<Follow>
    fun findAllByFromProfile(fromProfile: Profile): MutableList<Follow>
}