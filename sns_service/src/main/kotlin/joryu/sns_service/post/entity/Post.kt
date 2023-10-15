package joryu.sns_service.post.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import joryu.sns_service.common.entity.BaseEntity
import org.hibernate.Hibernate
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption

@Table(name = "post")
@Entity
class Post(
    content: String,
) : BaseEntity() {
    constructor() : this("")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0


    @OneToMany(mappedBy = "post", cascade = [CascadeType.REMOVE])
    private val postViews: List<PostView> = listOf()

    @Column(name = "content", nullable = false, length = 1000)
    var content: String = content
        private set

    @OneToMany(mappedBy = "post")
    private val postLikes: List<PostLike> = listOf()

    fun changeContent(content: String) {
        this.content = content
    }

    fun getViewCount(): Long {
        return Hibernate.size(postViews).toLong()
    }

    fun getLikeCount(): Long {
        return Hibernate.size(postLikes).toLong()
    }
}
