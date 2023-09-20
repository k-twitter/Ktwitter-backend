package joryu.sns_service.post.repository

import joryu.sns_service.post.entity.Post
import joryu.sns_service.post.entity.Share
import joryu.sns_service.profile.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ShareRepository : JpaRepository<Share, Long> {
    fun findAllByShareMember(shareMember: Profile): List<Share>
    fun deleteByShareMemberAndPost(shareMember: Profile, post: Post): Void
}
