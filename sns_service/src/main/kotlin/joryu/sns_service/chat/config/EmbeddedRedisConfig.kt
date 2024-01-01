package joryu.sns_service.chat.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer


@Profile("local") // profile이 local일때만 활성화
@Configuration
class EmbeddedRedisConfig {

    @Value("\${spring.data.redis.port}")
    private val redisPort = 6379

    private var redisServer: RedisServer? = null

    @PostConstruct
    fun redisServer() {
        redisServer = RedisServer(redisPort)
        redisServer?.start()
    }

    @PreDestroy
    fun stopRedis() {
        redisServer?.stop()
    }
}