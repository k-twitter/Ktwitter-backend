package joryu.sns_service.profile.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import joryu.sns_service.follower.entity.Follower
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest

@Table(name = "profile")
@Entity
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "profile_id")
        val id: Long,
        var name: String,

        @JsonManagedReference
        @OneToMany(mappedBy = "followingProfile")
        val followers: MutableList<Follower> = mutableListOf()
) {
    constructor() : this(0, "")
    constructor(name: String) : this(0, name)

    fun update(profileUpdateRequest: ProfileUpdateRequest) {
        this.name = profileUpdateRequest.name
    }

    fun addFollower(follower: Follower) {
        followers.add(follower)
    }
}

