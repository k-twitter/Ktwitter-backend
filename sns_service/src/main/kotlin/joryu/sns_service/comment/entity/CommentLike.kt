package joryu.sns_service.comment.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import joryu.sns_service.common.entity.BaseEntity
import joryu.sns_service.profile.entity.Profile

@Table(name = "comment_like")
@Entity
class CommentLike(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    val likeMember: Profile,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    val comment: Comment,
) : BaseEntity() {
    constructor() : this(Profile(), Comment())

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
