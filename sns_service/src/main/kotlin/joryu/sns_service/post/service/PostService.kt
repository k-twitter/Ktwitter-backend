package joryu.sns_service.post.service

import joryu.sns_service.post.entity.Post
import joryu.sns_service.post.entity.PostView
import joryu.sns_service.post.repository.PostRepository
import joryu.sns_service.post.repository.PostViewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val postViewRepository: PostViewRepository,
) {
    @Transactional
    fun create(content: String): Long {
        return postRepository.save(Post(content)).id
    }

    @Transactional
    fun findOneById(id: Long, ip: String?): Post {
        val findPost = postRepository.findById(id).orElseThrow()
        if (!ip.isNullOrEmpty() && haveNotSeenYet(findPost, ip)) {
            postViewRepository.save(PostView(findPost, ip))
        }
        return findPost
    }

    private fun haveNotSeenYet(findPost: Post, ip: String) = !postViewRepository.existsByPostAndIp(findPost, ip)

    @Transactional
    fun update(id: Long, newContent: String) {
        val postForUpdate = postRepository.findById(id).orElseThrow()
        postForUpdate.changeContent(newContent)
    }

    @Transactional
    fun delete(id: Long) {
        postRepository.deleteById(id)
    }
}
