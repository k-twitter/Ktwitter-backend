package joryu.sns_service.comment.dto.req

data class CommentCreateRequest(
    val postId: Long,
    val parentCommentId: Long?,
    val content: String
)
