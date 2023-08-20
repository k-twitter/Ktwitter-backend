package joryu.sns_service.profile.service

import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
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
        val profileRepository: ProfileRepository,
) {

    @Transactional
    fun create(profileCreateRequest: ProfileCreateRequest) : Profile{
        val profile = Profile(profileCreateRequest.name)
        profileRepository.save(profile)
        return profile
    }

    @Transactional(readOnly = true)
    fun findOneById(id: Long): ProfileInfoResponse {
        val profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        return ProfileInfoResponse(profile)
    }

    @Transactional
    fun update(id: Long, profileUpdateRequest: ProfileUpdateRequest): ProfileInfoResponse {
        var profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        profile.update(profileUpdateRequest)
        return ProfileInfoResponse(profile)
    }

    @Transactional
    fun delete(id: Long) {
        var profile = profileRepository.findById(id)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        profileRepository.delete(profile);
    }
}