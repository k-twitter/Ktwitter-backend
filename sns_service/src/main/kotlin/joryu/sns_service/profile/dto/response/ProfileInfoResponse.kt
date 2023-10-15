package joryu.sns_service.profile.dto.response

import joryu.sns_service.profile.entity.Profile

data class ProfileInfoResponse(
        val name: String?,
        val followerNumber: Int?,
        val followingNumber: Int?
) {
    constructor(profile: Profile?) : this(profile?.name, profile?.followerNumber, profile?.followingNumber)
}