package joryu.sns_service.post.dto.resp

import joryu.sns_service.post.entity.Post

data class PostResponse(
    val id: Long,
    val content: String,
    val viewCount: Long,
) {
    constructor(post: Post) : this(
        id = post.id,
        content = post.content,
        viewCount = post.viewCount
    )
}
