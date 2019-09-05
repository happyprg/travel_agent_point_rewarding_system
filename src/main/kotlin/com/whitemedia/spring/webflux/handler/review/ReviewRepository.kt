package com.whitemedia.spring.webflux.handler.review

import com.whitemedia.spring.webflux.handler.action.ActionType
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class ReviewRepository {
    fun save(review: Review): Review {
        return review
    }

    fun delete(reviewId: String, actionType: ActionType, modifiedAt: Timestamp): Review {
        return Review("reviewId", "userId", "placeId", "content", emptyArray(), ReviewStatusType.DELETED)
    }
}
