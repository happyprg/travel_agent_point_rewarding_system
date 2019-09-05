package com.whitemedia.spring.webflux.handler.event

import com.whitemedia.spring.webflux.handler.action.ActionType.DELETE
import com.whitemedia.spring.webflux.handler.review.ReviewService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.router

@Configuration
class EventLogRouter(val reviewService: ReviewService) {

    @Bean
    fun eventLogRoute() = router {
        (accept(APPLICATION_JSON)).nest {
            POST("/events") {
                it.bodyToMono(EventLog::class.java).filter(EventLog::isValid).flatMap { log ->
                    when (log.action) {
                        DELETE -> {
                            ok().body(fromObject(reviewService.delete(log)))
                        }
                        else -> {
                            ok().body(fromObject(reviewService.persist(log)))
                        }
                    }
                }
            }
        }
    }
}
