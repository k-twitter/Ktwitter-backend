package joryu.sns_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class SnsServiceApplication

fun main(args: Array<String>) {
    runApplication<SnsServiceApplication>(*args)
}
