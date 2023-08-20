package joryu.sns_service.follower.service

import joryu.sns_service.follower.dto.request.FollowRequest
import joryu.sns_service.follower.dto.request.UnFollowRequest
import joryu.sns_service.follower.dto.response.AllFollowingProfilesResponse
import joryu.sns_service.follower.entity.Follower
import joryu.sns_service.follower.repository.FollowerRepository
import joryu.sns_service.profile.exception.ProfileBaseException
import joryu.sns_service.profile.exception.ProfileExceptionEnums
import joryu.sns_service.profile.repository.ProfileRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class FollowerService(
        val followerRepository: FollowerRepository,
        val profileRepository: ProfileRepository
) {

    @Transactional
    fun followUp(followerProfileId: Long, followRequest: FollowRequest) {
        val requestProfile = profileRepository.findById(followRequest.profileId)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        val follower = Follower(requestProfile, followerProfileId)
        followerRepository.save(follower)
    }

    @Transactional
    fun unFollow(followerProfileId: Long, unFollowRequest: UnFollowRequest) {
        val requestProfile = profileRepository.findById(unFollowRequest.profileId)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        followerRepository.deleteByFollowingProfileAndFollowerProfileId(requestProfile, followerProfileId)
    }

    @Transactional(readOnly = true)
    fun getAllFollowingProfileByFollowerId(followerProfileId: Long) : List<AllFollowingProfilesResponse?> {
        val followingProfiles : MutableList<Follower> = followerRepository.findAllByFollowerProfileId(followerProfileId)
        return followingProfiles.map { profile -> profile.followingProfile?.let { AllFollowingProfilesResponse(it.id) } }
    }

}