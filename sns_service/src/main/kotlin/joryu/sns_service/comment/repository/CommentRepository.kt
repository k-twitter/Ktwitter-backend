package joryu.sns_service.comment.repository

import joryu.sns_service.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>
