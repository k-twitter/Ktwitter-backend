package joryu.sns_service.post.controller

import joryu.sns_service.post.dto.request.PostCreateRequest
import joryu.sns_service.post.dto.request.PostUpdateRequest
import joryu.sns_service.post.dto.response.PostResponse
import joryu.sns_service.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {
    @PostMapping
    fun createPost(@RequestBody req: PostCreateRequest): ResponseEntity<Void> {
        val postId = postService.create(req.content)
        return ResponseEntity.created(URI.create("/posts/${postId}")).build()
    }

    @GetMapping("/{id}")
    fun findPost(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val post = postService.findOneById(id)
        return ResponseEntity.ok(PostResponse(post))
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody req: PostUpdateRequest): ResponseEntity<Void> {
        postService.update(id, req.content)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Void> {
        postService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
