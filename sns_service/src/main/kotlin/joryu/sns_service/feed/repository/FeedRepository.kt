package joryu.sns_service.feed.repository

import joryu.sns_service.feed.entity.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository : JpaRepository<Feed, Long>
