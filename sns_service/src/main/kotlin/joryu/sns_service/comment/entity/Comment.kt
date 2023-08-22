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
import joryu.sns_service.feed.entity.Feed
import joryu.sns_service.common.entity.BaseEntity

@Table(name = "comment")
@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    val feed: Feed,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Comment?,

    content: String,
) : BaseEntity() {
    constructor() : this(0, Feed(), null, "")
    constructor(feed: Feed, parent: Comment) : this(0, feed, parent, "")

    @Column(name = "content", nullable = false, length = 1000)
    var content: String = content
        private set
}
