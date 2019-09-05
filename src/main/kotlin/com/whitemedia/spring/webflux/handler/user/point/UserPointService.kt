package com.whitemedia.spring.webflux.handler.user.point

import com.whitemedia.spring.webflux.handler.review.ReviewStatusType
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserPointService(val userPointRepository: UserPointRepository) {

    fun getTotalPoint(userId: String, reviewStatusTypes: Array<ReviewStatusType>): Mono<UserPoint> {
        return userPointRepository.getTotalPoint(userId, reviewStatusTypes)
    }
}
