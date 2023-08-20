package joryu.sns_service.profile.service

import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.dto.response.ProfileInfoResponse
import joryu.sns_service.profile.entity.Profile
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
    fun create(profileCreateRequest: ProfileCreateRequest) {
        val profile = Profile(profileCreateRequest.name)
        profileRepository.save(profile)
    }

    @Transactional(readOnly = true)
    fun findOnyById(id: Long): ProfileInfoResponse {
        val profile = profileRepository.findById(id)
                .orElseThrow()
        return ProfileInfoResponse(profile)
    }

    @Transactional
    fun update(id: Long, profileUpdateRequest: ProfileUpdateRequest): ProfileInfoResponse {
        var profile = profileRepository.findById(id)
                .orElseThrow()
        profile.update(profileUpdateRequest)
        return ProfileInfoResponse(profile)
    }

    @Transactional
    fun delete(id: Long) {
        profileRepository.deleteById(id);
    }
}