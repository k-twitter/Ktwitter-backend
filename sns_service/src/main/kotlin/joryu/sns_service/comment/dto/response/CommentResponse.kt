package joryu.sns_service.comment.dto.response

import joryu.sns_service.comment.entity.Comment

data class CommentResponse(
    val id: Long,
    val postId: Long,
    val parentCommentId: Long?,
    val content: String,
) {
    constructor(comment: Comment) : this(
        id = comment.id,
        postId = comment.post.id,
        parentCommentId = comment.parent?.id,
        content = comment.content,
    )
}
