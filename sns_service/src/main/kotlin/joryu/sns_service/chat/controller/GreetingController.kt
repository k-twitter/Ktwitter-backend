package joryu.sns_service.chat.controller

import joryu.sns_service.chat.message.GreetingMessage
import joryu.sns_service.chat.message.HelloMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/sub/greetings")
    fun greeting(message: HelloMessage): GreetingMessage {
        Thread.sleep(100)
        return GreetingMessage("Hello, " + message.name + "!");
    }
}