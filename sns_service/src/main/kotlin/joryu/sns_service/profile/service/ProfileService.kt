package joryu.sns_service.profile.service

import joryu.sns_service.follow.repository.FollowRepository
import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.dto.response.AllProfileResponse
import joryu.sns_service.profile.dto.response.ProfileInfoResponse
import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.exception.ProfileBaseException
import joryu.sns_service.profile.exception.ProfileExceptionEnums
import joryu.sns_service.profile.repository.ProfileRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class ProfileService(
        private val profileRepository: ProfileRepository,
        private val followRepository: FollowRepository
) {

    @Transactional
    fun create(profileCreateRequest: ProfileCreateRequest): ProfileInfoResponse {
        val profile = Profile(profileCreateRequest.name)
        profileRepository.save(profile)
        return ProfileInfoResponse(profile)
    }

    @Transactional(readOnly = true)
    fun findOneById(id: Long): ProfileInfoResponse {
        val profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        return ProfileInfoResponse(profile)
    }

    @Transactional
    fun update(id: Long, profileUpdateRequest: ProfileUpdateRequest): ProfileInfoResponse {
        val profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        profile.update(profileUpdateRequest)
        return ProfileInfoResponse(profile)
    }

    @Transactional
    fun delete(id: Long) {
        val profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        profileRepository.delete(profile);
    }

    @Transactional(readOnly = true)
    fun findAllFollowerProfileInfo(id: Long): AllProfileResponse {
        val profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        val followers = followRepository.findAllByToProfile(profile).stream()
                .map { follows -> follows.fromProfile }
                .toList()
        return AllProfileResponse().addProfileInfoInFollows(followers)
    }

    @Transactional(readOnly = true)
    fun findAllFollowingProfileInfo(id: Long): AllProfileResponse {
        val profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        val followings = followRepository.findAllByFromProfile(profile).stream()
                .map { follows -> follows.toProfile }
                .toList()
        return AllProfileResponse().addProfileInfoInFollows(followings)
    }
}