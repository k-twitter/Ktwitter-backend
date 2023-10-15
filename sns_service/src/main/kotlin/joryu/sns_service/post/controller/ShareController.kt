package joryu.sns_service.post.controller

import joryu.sns_service.post.dto.response.PostResponse
import joryu.sns_service.post.service.ShareService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/share")
class ShareController(
    private val shareService: ShareService
) {
    @PostMapping("/posts/{postId}/member/{memberId}")
    fun share(@PathVariable postId: Long, @PathVariable memberId: Long): ResponseEntity<Void> {
        shareService.share(postId, memberId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/posts/member/{memberId}")
    fun findAllSharePosts(@PathVariable memberId: Long): ResponseEntity<List<PostResponse>> {
        val shares = shareService.findAllSharePosts(memberId)
        return ResponseEntity.ok(shares.map { share -> PostResponse(share.post) })
    }

    @DeleteMapping("/posts/{postId}/member/{memberId}")
    fun cancelLike(@PathVariable postId: Long, @PathVariable memberId: Long): ResponseEntity<Void> {
        shareService.cancelShare(postId, memberId)
        return ResponseEntity.noContent().build()
    }
}
