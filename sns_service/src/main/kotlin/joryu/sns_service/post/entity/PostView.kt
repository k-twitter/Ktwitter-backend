package joryu.sns_service.post.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import joryu.sns_service.common.entity.BaseEntity

@Table(
    name = "post_view", indexes = [
        Index(name = "idx_post_view", columnList = "post_id, ip", unique = true)
    ]
)
@Entity
class PostView(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    val ip: String,
) : BaseEntity() {
    constructor() : this(Post(), "")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
