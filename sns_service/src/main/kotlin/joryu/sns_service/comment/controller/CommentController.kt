package joryu.sns_service.comment.controller

import joryu.sns_service.comment.dto.req.CommentCreateRequest
import joryu.sns_service.comment.dto.req.CommentUpdateRequest
import joryu.sns_service.comment.dto.resp.CommentResponse
import joryu.sns_service.comment.service.CommentService
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
@RequestMapping("/comments")
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping
    fun createComment(@RequestBody req: CommentCreateRequest): ResponseEntity<Void> {
        val commentId = commentService.create(req.postId, req.parentCommentId, req.content)
        return ResponseEntity.created(URI.create("/comments/${commentId}")).build()
    }

    @GetMapping("/{id}")
    fun findComment(@PathVariable id: Long): ResponseEntity<CommentResponse> {
        val comment = commentService.findOneById(id)
        return ResponseEntity.ok(CommentResponse(comment))
    }

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody req: CommentUpdateRequest): ResponseEntity<Void> {
        commentService.update(id, req.content)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Void> {
        commentService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
