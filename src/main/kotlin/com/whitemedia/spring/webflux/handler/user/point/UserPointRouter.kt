package com.whitemedia.spring.webflux.handler.user.point

import com.whitemedia.spring.webflux.handler.review.ReviewStatusType.NORMAL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router

@Configuration
class UserPointRouter(val userPointService: UserPointService) {

    val userIdPathName = "userId"
    @Bean
    fun userPointRoute() = router {
        (path("/user/{$userIdPathName}/point") and accept(APPLICATION_JSON)).nest {
            GET("") {
                ok().body(userPointService.getTotalPoint(it.pathVariable(userIdPathName), arrayOf(NORMAL)))
            }
        }
    }
}
