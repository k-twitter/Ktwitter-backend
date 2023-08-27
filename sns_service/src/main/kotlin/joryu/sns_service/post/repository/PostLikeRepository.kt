package joryu.sns_service.post.repository

import joryu.sns_service.post.entity.PostLike
import org.springframework.data.jpa.repository.JpaRepository

interface PostLikeRepository : JpaRepository<PostLike, Long>
