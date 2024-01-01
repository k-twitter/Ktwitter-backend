package joryu.sns_service.profile.repository

import joryu.sns_service.profile.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {
    fun findAllByIdIn(ids: List<Long>): List<Profile>
}