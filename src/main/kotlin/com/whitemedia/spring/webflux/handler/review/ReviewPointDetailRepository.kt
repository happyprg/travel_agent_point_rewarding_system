package com.whitemedia.spring.webflux.handler.review

import org.springframework.stereotype.Repository

@Repository
class ReviewPointDetailRepository {
    fun save(reviewPointDetail: ReviewPointDetail): ReviewPointDetail {
        return reviewPointDetail
    }

    fun delete(reviewId: String, contentPoint: Int, attachPhotoPoint: Int, firstReviewPoint: Int, totalPoint: Long): ReviewPointDetail {
        return ReviewPointDetail(reviewId, contentPoint, attachPhotoPoint, firstReviewPoint, totalPoint)
    }
}
