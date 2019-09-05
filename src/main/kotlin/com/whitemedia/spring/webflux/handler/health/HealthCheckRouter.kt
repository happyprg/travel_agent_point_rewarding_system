package com.whitemedia.spring.webflux.handler.health

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.router

@Configuration
class HealthCheckRouter {

    @Bean
    fun healthRoute() = router {
        (accept(APPLICATION_JSON) and "/health").nest {
            GET("/check") { ok().body(fromObject("ok")) }
        }
    }
}
