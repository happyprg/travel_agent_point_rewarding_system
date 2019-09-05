package com.whitemedia.spring.webflux.handler.review

import java.sql.Timestamp
import java.sql.Timestamp.from
import java.time.Instant.now

data class ReviewPointDetailHistory private constructor(
        val historyId: Long? = null,
        val reviewId: String,
        val userId: String,
        val contentPoint: Int,
        val attachPhotoPoint: Int,
        val firstReviewPoint: Int,
        val totalPoint: Long,
        val createdAt: Timestamp = from(now())
) {
    companion object {
        fun of(userId: String, reviewPointDetail: ReviewPointDetail): ReviewPointDetailHistory {
            reviewPointDetail.run {
                return ReviewPointDetailHistory(null, reviewId, userId, contentPoint, attachPhotoPoint, firstReviewPoint, totalPoint)
            }
        }
    }
}