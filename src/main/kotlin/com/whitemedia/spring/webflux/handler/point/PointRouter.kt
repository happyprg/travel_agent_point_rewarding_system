package com.whitemedia.spring.webflux.handler.point

import com.whitemedia.spring.webflux.handler.action.ActionType.DELETE
import com.whitemedia.spring.webflux.handler.event.EventLog
import com.whitemedia.spring.webflux.handler.review.ReviewService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.router

@Configuration
class PointRouter(val reviewService: ReviewService) {

    @Bean
    fun pointRoute() = router {
        (accept(APPLICATION_JSON) and path("/point")).nest {
            POST("") {
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
