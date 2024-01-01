package joryu.sns_service.post.repository

import joryu.sns_service.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
