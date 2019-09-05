package com.whitemedia.spring.webflux.handler.review

data class ReviewPointDetail constructor(
        val reviewId: String,
        val contentPoint: Int,
        val attachPhotoPoint: Int,
        val firstReviewPoint: Int,
        val totalPoint: Long
) {
    companion object {
        fun of(review: Review, isFirstReview: Boolean): ReviewPointDetail {
            review.run {
                val contentPoint = if (content.isNotEmpty()) 1 else 0
                val attachPhotoPoint = if (attachPhotoIds.isNotEmpty()) 1 else 0
                val firstReviewPoint = if (isFirstReview) 1 else 0
                return ReviewPointDetail(review.reviewId, contentPoint, attachPhotoPoint, firstReviewPoint,
                        intArrayOf(contentPoint, attachPhotoPoint, firstReviewPoint).sum().toLong()
                )
            }
        }
    }
}