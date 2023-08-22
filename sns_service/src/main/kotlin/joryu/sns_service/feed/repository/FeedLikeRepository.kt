package joryu.sns_service.feed.repository

import joryu.sns_service.feed.entity.FeedLike
import org.springframework.data.jpa.repository.JpaRepository

interface FeedLikeRepository : JpaRepository<FeedLike, Long>
