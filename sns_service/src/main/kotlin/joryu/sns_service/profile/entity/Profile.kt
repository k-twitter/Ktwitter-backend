package joryu.sns_service.profile.entity

import jakarta.persistence.*
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest

@Table(name = "profile")
@Entity
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        var name: String
) {
    constructor() : this(0, "")
    constructor(name: String) : this(0, name)

    fun update(profileUpdateRequest: ProfileUpdateRequest) {
        this.name = profileUpdateRequest.name
    }
}