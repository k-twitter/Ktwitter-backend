package joryu.sns_service.feed.repository

import joryu.sns_service.feed.entity.Share
import org.springframework.data.jpa.repository.JpaRepository

interface ShareRepository : JpaRepository<Share, Long>
