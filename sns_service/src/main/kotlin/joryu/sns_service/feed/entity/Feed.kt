package joryu.sns_service.feed.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import joryu.sns_service.common.entity.BaseEntity

@Table(name = "feed")
@Entity
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    content: String,
) : BaseEntity() {
    constructor() : this(0, "")
    constructor(content: String) : this(0, content)

    @Column(name = "view_count", nullable = false)
    var viewCount: Long = 0
        private set

    @Column(name = "content", nullable = false, length = 1000)
    var content: String = content
        private set
}
