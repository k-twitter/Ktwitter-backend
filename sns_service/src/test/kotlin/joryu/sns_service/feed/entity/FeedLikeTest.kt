package joryu.sns_service.feed.entity

import jakarta.persistence.EntityManager
import joryu.sns_service.feed.repository.FeedLikeRepository
import joryu.sns_service.feed.repository.FeedRepository
import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.repository.ProfileRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
// @Disabled
class FeedLikeTest {

    @Autowired
    lateinit var em: EntityManager

    @Autowired
    lateinit var profileRepository: ProfileRepository

    @Autowired
    lateinit var feedRepository: FeedRepository

    @Autowired
    lateinit var feedLikeRepository: FeedLikeRepository

    @Test
    fun `연관관계가 잘 적용되어야 한다`() {
        val profile = profileRepository.save(Profile("현모"))
        val feed = feedRepository.save(Feed("피드내용"))
        val feedLike = feedLikeRepository.save(FeedLike(profile, feed))

        em.flush()
        em.clear()

        val findFeedLikeEntity = feedLikeRepository.getReferenceById(feedLike.id)

        assertThat(findFeedLikeEntity).isNotNull()
        assertThat(findFeedLikeEntity.likeMember.name).isEqualTo(profile.name)
        assertThat(findFeedLikeEntity.feed.content).isEqualTo(feed.content)
    }
}
