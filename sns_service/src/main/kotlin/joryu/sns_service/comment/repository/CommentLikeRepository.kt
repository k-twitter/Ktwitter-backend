package joryu.sns_service.comment.repository

import joryu.sns_service.comment.entity.CommentLike
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeRepository : JpaRepository<CommentLike, Long>
