package joryu.sns_service.post.service

import joryu.sns_service.post.entity.Share
import joryu.sns_service.post.repository.PostLikeRepository
import joryu.sns_service.post.repository.PostRepository
import joryu.sns_service.post.repository.ShareRepository
import joryu.sns_service.profile.repository.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ShareService(
    private val shareRepository: ShareRepository,
    private val profileRepository: ProfileRepository,
    private val postRepository: PostRepository,
) {
    @Transactional
    fun share(postId: Long, memberId: Long) {
        val member = profileRepository.findById(memberId).orElseThrow()
        val post = postRepository.findById(postId).orElseThrow()
        shareRepository.save(Share(member, post))
    }

    fun findAllSharePosts(memberId: Long): List<Share> {
        val member = profileRepository.findById(memberId).orElseThrow()
        return shareRepository.findAllByShareMember(member)
    }

    @Transactional
    fun cancelShare(postId: Long, memberId: Long) {
        val member = profileRepository.findById(memberId).orElseThrow()
        val post = postRepository.findById(postId).orElseThrow()
        shareRepository.deleteByShareMemberAndPost(member, post)
    }
}
