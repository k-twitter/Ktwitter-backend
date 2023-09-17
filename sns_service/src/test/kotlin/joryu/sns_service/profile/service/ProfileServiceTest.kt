package joryu.sns_service.profile.service

import joryu.sns_service.follow.entity.Follow
import joryu.sns_service.follow.repository.FollowRepository
import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.exception.ProfileBaseException
import joryu.sns_service.profile.repository.ProfileRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ProfileServiceTest (
        @Autowired
        private val profileRepository: ProfileRepository,
        @Autowired
        private val profileService: ProfileService,
        @Autowired
        private val followRepository: FollowRepository
) {
    @Test
    fun `profile 생성 후 저장`() {
        // given
        val profileCreateRequest = ProfileCreateRequest("조정현")

        // when
        val response = profileService.create(profileCreateRequest)

        // then
        assertEquals(profileCreateRequest.name, response.name)
        assertEquals(response.followerNumber, 0)
        assertEquals(response.followingNumber, 0)
    }

    @Test
    fun `profile ID로 조회 성공`() {
        // given
        val profile = Profile("조정현")
        profileRepository.save(profile)

        // when
        val response = profileService.findOneById(profile.id)

        // then
        assertEquals(profile.name, response.name)
        assertEquals(profile.followerNumber, response.followerNumber)
        assertEquals(profile.followingNumber, response.followingNumber)
    }

    @Test
    fun `profile ID로 조회 실패`() {
        // given
        val profile = Profile("조정현")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileService.findOneById(profile.id)
        }
    }

    @Test
    fun `profile ID로 프로필 정보 업데이트 성공`() {
        // given
        val profile = Profile("조정현")
        val profileUpdateRequest = ProfileUpdateRequest("조현모")
        profileRepository.save(profile)


        // when
        val response = profileService.update(profile.id, profileUpdateRequest)

        // then
        assertEquals(profileUpdateRequest.name, response.name)
        assertEquals(profile.followerNumber, response.followerNumber)
        assertEquals(profile.followingNumber, response.followingNumber)
    }

    @Test
    fun `profile ID로 프로필 정보 업데이트 실패`() {
        // given
        val profile = Profile("조정현")
        val profileUpdateRequest = ProfileUpdateRequest("조현모")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileService.update(profile.id, profileUpdateRequest)
        }
    }

    @Test
    fun `profile ID로 프로필 삭제 성공`() {
        // given
        val profile = Profile("조정현")
        profileRepository.save(profile)


        // when
        profileService.delete(profile.id)

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileService.findOneById(profile.id)
        }
    }

    @Test
    fun `profile ID로 프로필 삭제 실패`() {
        // given
        val profile = Profile("조정현")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileService.delete(profile.id)
        }
    }

    @Test
    fun `profile ID로 모든 팔로워 프로필 조회 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)
        profileRepository.save(toProfile)
        profileRepository.save(fromProfile)
        followRepository.save(follow)

        // when
        val response = profileService.findAllFollowerProfileInfo(toProfile.id)

        // then
        assertEquals(response.profileInfo[0].name, fromProfile.name)
        assertEquals(response.profileInfo[0].followingNumber, 1)
        assertEquals(toProfile.followerNumber, 1)
    }

    @Test
    fun `profile ID로 모든 팔로워 프로필 조회 실패`() {
        // given
        val profile = Profile("조정현")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileService.findAllFollowerProfileInfo(profile.id)
        }
    }

    @Test
    fun `profile ID로 모든 팔로잉 프로필 조회 성공`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")
        val follow = Follow(fromProfile, toProfile)
        profileRepository.save(toProfile)
        profileRepository.save(fromProfile)
        followRepository.save(follow)

        // when
        val response = profileService.findAllFollowingProfileInfo(fromProfile.id)

        // then
        assertEquals(response.profileInfo[0].name, toProfile.name)
        assertEquals(response.profileInfo[0].followerNumber, 1)
        assertEquals(fromProfile.followingNumber, 1)
    }

    @Test
    fun `profile ID로 모든 팔로잉 프로필 조회 실패`() {
        // given
        val profile = Profile("조정현")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileService.findAllFollowingProfileInfo(profile.id)
        }
    }

}