package joryu.sns_service.post.repository

import joryu.sns_service.post.entity.Share
import org.springframework.data.jpa.repository.JpaRepository

interface ShareRepository : JpaRepository<Share, Long>
