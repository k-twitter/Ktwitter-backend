package joryu.sns_service.comment.service

import joryu.sns_service.comment.entity.Comment
import joryu.sns_service.comment.repository.CommentRepository
import joryu.sns_service.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    val postRepository: PostRepository,
    val commentRepository: CommentRepository,
) {
    @Transactional
    fun create(postId: Long, parentCommentId: Long?, content: String): Long {
        val post = postRepository.findById(postId).orElseThrow()
        val parentComment = parentCommentId?.let { commentRepository.findById(it).orElseThrow() }
        return commentRepository.save(Comment(post, parentComment, content)).id
    }

    fun findOneById(id: Long): Comment {
        return commentRepository.findById(id).orElseThrow()
    }

    @Transactional
    fun update(id: Long, newContent: String) {
        val commentForUpdate = commentRepository.findById(id).orElseThrow()
        commentForUpdate.changeContent(newContent)
    }

    @Transactional
    fun delete(id: Long) {
        commentRepository.deleteById(id)
    }
}
