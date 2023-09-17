package joryu.sns_service.chat.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


/**
 * WebSocket 설정을 관리하는 클래스
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    /**
     * 목적지 prefix 가 "/sub" 인 메시지를 필터링합니다. (outbound)
     * 도착지 prefix 가 "/pub" 인 메시지를 필터링합니다. (inbound)
     */
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/sub")
        config.setApplicationDestinationPrefixes("/pub")
    }

    /**
     * STOMP endpoint 를 설정합니다.
     */
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/ws-stomp")
            .setAllowedOriginPatterns("*")
    }
}