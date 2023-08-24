package joryu.sns_service.feed.entity

import jakarta.persistence.EntityManager
import joryu.sns_service.feed.repository.FeedRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@SpringBootTest
@Disabled("프로젝트 초기 kotlin entity 테스트용도. springBootTest라 속도가 느려서 disabled.")
class FeedEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Autowired
    lateinit var feedRepository: FeedRepository

    @Test
    fun `BaseEntity에 createAt, updateAt이 잘 들어가야 한다`() {
        val before = LocalDateTime.now()
        val feedEntity = feedRepository.save(Feed("123"))

        em.flush()
        em.clear()

        val findFeedEntity = feedRepository.getReferenceById(feedEntity.id)

        assertThat(findFeedEntity.createAt).isNotNull()
        assertThat(findFeedEntity.createAt).isAfter(before)
        assertThat(findFeedEntity.createAt).isBefore(LocalDateTime.now())
        assertThat(findFeedEntity.updateAt).isNotNull()
        assertThat(findFeedEntity.updateAt).isAfter(before)
        assertThat(findFeedEntity.updateAt).isBefore(LocalDateTime.now())
    }

    @Test
    fun `BaseEntity의 Id는 auto increment 되어야 한다`() {
        val feedEntity1 = feedRepository.save(Feed("123"))
        val feedEntity2 = feedRepository.save(Feed("123"))
        val feedEntity3 = feedRepository.save(Feed("123"))

        assertThat(feedEntity1.id).isLessThan(feedEntity2.id)
        assertThat(feedEntity2.id).isLessThan(feedEntity3.id)
    }
}
