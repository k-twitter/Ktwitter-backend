package joryu.sns_service.comment.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import joryu.sns_service.common.entity.BaseEntity
import joryu.sns_service.post.entity.Post

@Table(name = "comment")
@Entity
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Comment?,

    content: String,
) : BaseEntity() {
    constructor() : this(Post(), null, "")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(name = "content", nullable = false, length = 1000)
    var content: String = content
        private set

    fun changeContent(content: String) {
        this.content = content
    }
}
