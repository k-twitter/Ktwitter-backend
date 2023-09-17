package joryu.sns_service.comment.dto.request

data class CommentCreateRequest(
    val postId: Long,
    val parentCommentId: Long?,
    val content: String
)
