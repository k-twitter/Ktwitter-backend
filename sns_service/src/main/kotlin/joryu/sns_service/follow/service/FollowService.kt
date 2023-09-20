package joryu.sns_service.follow.service

import joryu.sns_service.follow.entity.Follow
import joryu.sns_service.follow.repository.FollowRepository
import joryu.sns_service.profile.exception.ProfileBaseException
import joryu.sns_service.profile.exception.ProfileExceptionEnums
import joryu.sns_service.profile.repository.ProfileRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class FollowService(
        private val followRepository: FollowRepository,
        private val profileRepository: ProfileRepository
) {

    @Transactional
    fun follow(fromProfileId: Long, toProfileId: Long) {
        val fromProfile = profileRepository.findById(fromProfileId)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        val toProfile = profileRepository.findById(toProfileId)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        val follow = Follow(fromProfile, toProfile)
        followRepository.save(follow)
    }

    @Transactional
    fun unFollow(fromProfileId: Long, toProfileId: Long) {
        val fromProfile = profileRepository.findById(fromProfileId)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        val toProfile = profileRepository.findById(toProfileId)
                .orElseThrow { ProfileBaseException(ProfileExceptionEnums.PROFILE_NOT_FOUND) }
        followRepository.deleteByFromProfileAndToProfile(fromProfile, toProfile)
    }
}