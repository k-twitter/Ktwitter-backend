package joryu.sns_service.post.repository

import joryu.sns_service.post.entity.Post
import joryu.sns_service.post.entity.PostLike
import joryu.sns_service.profile.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository : JpaRepository<PostLike, Long> {
    fun countByPost(post: Post): Long
    fun deleteByLikeMemberAndPost(likeMember: Profile, post: Post): Void
}
