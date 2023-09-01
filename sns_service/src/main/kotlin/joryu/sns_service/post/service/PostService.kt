package joryu.sns_service.post.service

import joryu.sns_service.post.entity.Post
import joryu.sns_service.post.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    val postRepository: PostRepository
) {
    @Transactional
    fun create(content: String): Long {
        return postRepository.save(Post(content)).id
    }

    fun findOneById(id: Long): Post {
        return postRepository.findById(id).orElseThrow()
    }

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
