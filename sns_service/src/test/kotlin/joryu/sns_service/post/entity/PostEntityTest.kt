package joryu.sns_service.post.entity

import jakarta.persistence.EntityManager
import joryu.sns_service.post.repository.PostRepository
import joryu.sns_service.post.entity.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@SpringBootTest
// @Disabled("프로젝트 초기 kotlin entity 테스트용도. springBootTest라 속도가 느려서 disabled.")
class PostEntityTest {

    @Autowired
    lateinit var em: EntityManager

    @Autowired
    lateinit var postRepository: PostRepository

    @Test
    fun `BaseEntity에 createAt, updateAt이 잘 들어가야 한다`() {
        val before = LocalDateTime.now()
        val postEntity = postRepository.save(Post("123"))

        em.flush()
        em.clear()

        val findPostEntity = postRepository.getReferenceById(postEntity.id)

        assertThat(findPostEntity.createAt).isNotNull()
        assertThat(findPostEntity.createAt).isAfter(before)
        assertThat(findPostEntity.createAt).isBefore(LocalDateTime.now())
        assertThat(findPostEntity.updateAt).isNotNull()
        assertThat(findPostEntity.updateAt).isAfter(before)
        assertThat(findPostEntity.updateAt).isBefore(LocalDateTime.now())
    }

    @Test
    fun `BaseEntity의 Id는 auto increment 되어야 한다`() {
        val postEntity1 = postRepository.save(Post("123"))
        val postEntity2 = postRepository.save(Post("123"))
        val postEntity3 = postRepository.save(Post("123"))

        assertThat(postEntity1.id).isLessThan(postEntity2.id)
        assertThat(postEntity2.id).isLessThan(postEntity3.id)
    }
}
