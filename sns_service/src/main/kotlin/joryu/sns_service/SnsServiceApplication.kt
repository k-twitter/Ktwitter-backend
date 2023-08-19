package joryu.sns_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SnsServiceApplication

fun main(args: Array<String>) {
	runApplication<SnsServiceApplication>(*args)
}
