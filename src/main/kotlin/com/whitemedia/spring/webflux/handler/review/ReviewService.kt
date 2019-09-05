package com.whitemedia.spring.webflux.handler.review

import com.whitemedia.spring.webflux.handler.action.ActionType.DELETE
import com.whitemedia.spring.webflux.handler.event.EventLog
import com.whitemedia.spring.webflux.handler.review.ReviewStatusType.NORMAL
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp.from
import java.time.Instant

@Component
class ReviewService(
        val reviewRepository: ReviewRepository,
        val reviewPointDetailRepository: ReviewPointDetailRepository,
        val reviewPointDetailHistoryRepository: ReviewPointDetailHistoryRepository
) {

    @Transactional
    fun delete(eventLog: EventLog): Triple<Review, ReviewPointDetail, Any> {
        // TODO Consider exceptional case
        eventLog.run {
            val deletedDetail = reviewRepository.delete(reviewId, DELETE, from(Instant.now()))
            val deletedReviewPointDetail = reviewPointDetailRepository.delete(reviewId, 0, 0, 0, 0)
            val deletedReviewPointDetailHistory = reviewPointDetailHistoryRepository.save(ReviewPointDetailHistory.of(userId, deletedReviewPointDetail))
            return Triple(deletedDetail, deletedReviewPointDetail, deletedReviewPointDetailHistory)
        }
    }

    @Transactional
    fun persist(eventLog: EventLog): Triple<Review, ReviewPointDetail, ReviewPointDetailHistory> {
        val countBy = countBy(eventLog.placeId, NORMAL)
        val convertToReview = eventLog.convertToReview(countBy.compareTo(0L) == 0)
        reviewRepository.save(convertToReview.first)
        reviewPointDetailRepository.save(convertToReview.second)
        reviewPointDetailHistoryRepository.save(convertToReview.third)
        return convertToReview
    }

    // MySql connector does not support Reactive stream not yet.
    fun countBy(placeId: String, statusType: ReviewStatusType): Long {
        return 0L
    }
}
