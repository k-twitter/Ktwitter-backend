package joryu.sns_service.follow.entity

import joryu.sns_service.profile.entity.Profile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FollowTest {

    @Test
    fun `follow 생성`() {
        // given
        val fromProfile = Profile("팔로워")
        val toProfile = Profile("팔로잉")

        // when
        val follow = Follow(fromProfile, toProfile)

        // then
        assertEquals(follow.fromProfile, fromProfile)
        assertEquals(follow.toProfile, toProfile)
        assertEquals(fromProfile.followingNumber, 1)
        assertEquals(toProfile.followerNumber, 1)
    }
}