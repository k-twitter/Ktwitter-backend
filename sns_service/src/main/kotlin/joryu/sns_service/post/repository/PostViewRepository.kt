package joryu.sns_service.post.repository

import joryu.sns_service.post.entity.Post
import joryu.sns_service.post.entity.PostView
import org.springframework.data.jpa.repository.JpaRepository

interface PostViewRepository : JpaRepository<PostView, Long> {
    fun existsByPostAndIp(post: Post, ip: String): Boolean
}
