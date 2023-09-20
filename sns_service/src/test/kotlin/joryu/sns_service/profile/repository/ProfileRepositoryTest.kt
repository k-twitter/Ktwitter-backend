package joryu.sns_service.profile.repository

import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.exception.ProfileBaseException
import joryu.sns_service.profile.exception.ProfileExceptionEnums
import joryu.sns_service.profile.exception.ProfileExceptionEnums.PROFILE_NOT_FOUND
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ProfileRepositoryTest(
        @Autowired
        private val profileRepository: ProfileRepository
) {

    @Test
    fun `profile 저장`() {
        // given
        val profile = Profile("조정현")

        // when
        profileRepository.save(profile)

        // then
    }

    @Test
    fun `profile ID로 조회 성공`() {
        // given
        val profile = Profile("조정현")
        profileRepository.save(profile)

        // when
        val findProfile = profileRepository.findById(profile.id)
                .orElseThrow{ProfileBaseException(PROFILE_NOT_FOUND)}

        // then
        Assertions.assertEquals(profile, findProfile)
    }

    @Test
    fun `profile ID로 조회 실패`() {
        // given
        val profile = Profile("조정현")

        // when

        // then
        assertThrows(ProfileBaseException::class.java) {
            profileRepository.findById(profile.id)
                    .orElseThrow { ProfileBaseException(PROFILE_NOT_FOUND) }
        }
    }
}