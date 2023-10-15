package joryu.sns_service.chat.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * 채팅에 사용되는 redis 설정 관리
 */
@Configuration
@EnableRedisRepositories
class RedisConfig(
    @Value("\${spring.data.redis.port:6379}")
    private val port: Int,

    @Value("\${spring.data.redis.host:localhost}")
    private val host: String
) {

    /**
     * ChannelTopic 에 발행된 메시지를 처리하는 Listner 들을 설정한다.
     */
    @Bean
    @Primary
    fun redisMessageListenerContainer(
        redisConnectionFactory: RedisConnectionFactory,
        chatMessageListenerAdapter: MessageListenerAdapter,
        @Qualifier("chatChannelTopic") chatChannelTopic: ChannelTopic
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
        container.addMessageListener(chatMessageListenerAdapter, chatChannelTopic)
        return container
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = host
        redisStandaloneConfiguration.port = port
        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

    /**
     * RedisMessageListenerContainer 로부터 메시지를 전달받는다.
     * 메시지 처리 비즈니스 로직을 담은 Subscriber Bean 을 추가해준다.
     */
    @Bean
    fun chatMessageListenerAdapter(listener: MessageListener): MessageListenerAdapter {
        return MessageListenerAdapter(listener, "onMessage")
    }

    /**
     * 채팅 채널 토픽을 반환한다.
     */
    @Bean(value = ["chatChannelTopic"])
    fun chatChannelTopic(): ChannelTopic {
        return ChannelTopic("chat")
    }

    /**
     * Redis 데이터에 접근하는 redisTemplate 를 반환한다.
     */
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = connectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
        return redisTemplate
    }
}