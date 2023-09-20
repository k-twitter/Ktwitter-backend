package joryu.sns_service.follow.repository

import joryu.sns_service.follow.entity.Follow
import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.repository.ProfileRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class FollowRepositoryTest(
        @Autowired
        private val followRepository: FollowRepository,
        @Autowired
        private val profileRepository: ProfileRepository
) {

    @Test
    fun `follow 저장`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)

        // when
        followRepository.save(follow)

        // then
    }

    @Test
    fun `fromProfile과 toProfile로 follow 삭제 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)
        profileRepository.save(fromProfile)
        profileRepository.save(toProfile)
        followRepository.save(follow)

        // when
        followRepository.deleteByFromProfileAndToProfile(fromProfile, toProfile)

        // then
    }

    @Test
    fun `fromProfile로 follow 조회 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)
        profileRepository.save(fromProfile)
        profileRepository.save(toProfile)
        followRepository.save(follow)

        // when
        val findFollow = followRepository.findAllByFromProfile(fromProfile)[0]

        // then
        Assertions.assertEquals(findFollow, follow)
    }

    @Test
    fun `toProfile로 follow 조회 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)
        profileRepository.save(fromProfile)
        profileRepository.save(toProfile)
        followRepository.save(follow)

        // when
        val findFollow = followRepository.findAllByToProfile(toProfile)[0]

        // then
        Assertions.assertEquals(findFollow, follow)
    }

}