package com.whitemedia.spring.webflux.handler.user.point

import com.whitemedia.spring.webflux.handler.point.PointType.REVIEW
import com.whitemedia.spring.webflux.handler.review.ReviewStatusType
import org.apache.commons.lang3.RandomUtils.nextLong
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserPointRepository {
    fun getTotalPoint(userId: String, reviewStatusTypes: Array<ReviewStatusType>): Mono<UserPoint> {
        return Mono.just(UserPoint(userId, REVIEW, nextLong())) // To change body of created functions use File | Settings | File Templates.
    }
}
