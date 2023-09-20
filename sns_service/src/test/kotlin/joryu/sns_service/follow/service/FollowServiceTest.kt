package joryu.sns_service.follow.service

import joryu.sns_service.follow.entity.Follow
import joryu.sns_service.follow.repository.FollowRepository
import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.exception.ProfileBaseException
import joryu.sns_service.profile.repository.ProfileRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class FollowServiceTest (
        @Autowired
        private val followService: FollowService,
        @Autowired
        private val followRepository: FollowRepository,
        @Autowired
        private val profileRepository: ProfileRepository
){

    @Test
    fun `팔로우 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        profileRepository.save(fromProfile)
        profileRepository.save(toProfile)

        // when
        followService.follow(fromProfile.id, toProfile.id)

        // then
    }

    @Test
    fun `팔로우 실패`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            followService.follow(fromProfile.id, toProfile.id)
        }
    }

    @Test
    fun `언팔로우 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)
        profileRepository.save(fromProfile)
        profileRepository.save(toProfile)
        followRepository.save(follow)

        // when
        followService.unFollow(fromProfile.id, toProfile.id)

        // then
    }

    @Test
    fun `언팔로우 실패`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            followService.unFollow(fromProfile.id, toProfile.id)
        }
    }
}