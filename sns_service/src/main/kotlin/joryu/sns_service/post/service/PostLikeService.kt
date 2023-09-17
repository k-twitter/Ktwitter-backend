package joryu.sns_service.post.service

import joryu.sns_service.post.entity.PostLike
import joryu.sns_service.post.repository.PostLikeRepository
import joryu.sns_service.post.repository.PostRepository
import joryu.sns_service.profile.repository.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostLikeService(
    val postLikeRepository: PostLikeRepository,
    val profileRepository: ProfileRepository,
    val postRepository: PostRepository,
) {
    @Transactional
    fun like(postId: Long, memberId: Long) {
        val member = profileRepository.findById(memberId).orElseThrow()
        val post = postRepository.findById(postId).orElseThrow()
        postLikeRepository.save(PostLike(member, post))
    }

    fun getCountByPost(postId: Long): Long {
        val post = postRepository.findById(postId).orElseThrow()
        return postLikeRepository.countByPost(post)
    }

    @Transactional
    fun cancelLike(postId: Long, memberId: Long) {
        val member = profileRepository.findById(memberId).orElseThrow()
        val post = postRepository.findById(postId).orElseThrow()
        postLikeRepository.deleteByLikeMemberAndPost(member, post)
    }
}
