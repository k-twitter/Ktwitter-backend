package joryu.sns_service.profile.entity

import joryu.sns_service.follow.entity.Follow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProfileTest {

    @Test
    fun `profile 생성`() {
        // given
        val profile = Profile("조정현")

        // when

        // then
        assertEquals(profile.name, "조정현")
        assertEquals(profile.followerNumber, 0)
        assertEquals(profile.followingNumber, 0)
    }

    @Test
    fun `팔로우 관계 추가`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")

        // when
        val follow = Follow(fromProfile, toProfile)

        // then
        assertEquals(fromProfile.followingNumber, 1)
        assertEquals(toProfile.followerNumber, 1)
        assertEquals(fromProfile.followings[0].toProfile, toProfile)
        assertEquals(toProfile.followers[0].fromProfile, fromProfile)
    }
}