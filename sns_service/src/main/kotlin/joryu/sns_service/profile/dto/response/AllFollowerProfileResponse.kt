package joryu.sns_service.profile.dto.response

import joryu.sns_service.profile.entity.Profile

data class AllProfileResponse(
        val profileInfo: MutableList<ProfileInfoResponse> = mutableListOf()
) {

    fun addProfileInfoInFollows(profiles: List<Profile?>): AllProfileResponse {
        profiles.forEach { profile -> profileInfo.add(ProfileInfoResponse(profile)) }
        return this
    }
}