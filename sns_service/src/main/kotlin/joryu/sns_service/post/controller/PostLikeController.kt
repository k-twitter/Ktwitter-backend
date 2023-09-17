package joryu.sns_service.post.controller

import joryu.sns_service.post.dto.response.PostLikeCountResponse
import joryu.sns_service.post.service.PostLikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/like")
class PostLikeController(
    private val postLikeService: PostLikeService
) {
    @PostMapping("/posts/{postId}/member/{memberId}")
    fun like(@PathVariable postId: Long, @PathVariable memberId: Long): ResponseEntity<Void> {
        postLikeService.like(postId, memberId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/posts/{postId}")
    fun getCountByPost(@PathVariable postId: Long): ResponseEntity<PostLikeCountResponse> {
        val likeCount = postLikeService.getCountByPost(postId)
        return ResponseEntity.ok(PostLikeCountResponse(likeCount))
    }

    @DeleteMapping("/posts/{postId}/member/{memberId}")
    fun cancelLike(@PathVariable postId: Long, @PathVariable memberId: Long): ResponseEntity<Void> {
        postLikeService.cancelLike(postId, memberId)
        return ResponseEntity.noContent().build()
    }
}
