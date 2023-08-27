package joryu.sns_service.post.entity

import jakarta.persistence.EntityManager
import joryu.sns_service.post.repository.PostLikeRepository
import joryu.sns_service.post.repository.PostRepository
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
class PostLikeTest {

    @Autowired
    lateinit var em: EntityManager

    @Autowired
    lateinit var profileRepository: ProfileRepository

    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var postLikeRepository: PostLikeRepository

    @Test
    fun `연관관계가 잘 적용되어야 한다`() {
        val profile = profileRepository.save(Profile("현모"))
        val post = postRepository.save(Post("피드내용"))
        val postLike = postLikeRepository.save(PostLike(profile, post))

        em.flush()
        em.clear()

        val findPostLikeEntity = postLikeRepository.getReferenceById(postLike.id)

        assertThat(findPostLikeEntity).isNotNull()
        assertThat(findPostLikeEntity.likeMember.name).isEqualTo(profile.name)
        assertThat(findPostLikeEntity.post.content).isEqualTo(post.content)
    }
}
