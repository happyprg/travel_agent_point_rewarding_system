package com.whitemedia.spring.webflux.handler.event

import com.whitemedia.spring.webflux.handler.action.ActionType
import com.whitemedia.spring.webflux.handler.review.Review
import com.whitemedia.spring.webflux.handler.review.ReviewPointDetail
import com.whitemedia.spring.webflux.handler.review.ReviewPointDetailHistory
import com.whitemedia.spring.webflux.handler.review.ReviewStatusType.NORMAL

// {
//    "type": "REVIEW",
//    "action": "ADD", /* "MOD", "DELETE" */
//    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
//    "content": "좋아요!",
//    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
//    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
//    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
// }
data class EventLog(
        val type: EventLogType,
        val action: ActionType,
        val reviewId: String,
        val content: String?,
        val attachedPhotoIds: Array<String>?,
        val userId: String,
        val placeId: String
) {
    fun convertToReview(isFirstReview: Boolean): Triple<Review, ReviewPointDetail, ReviewPointDetailHistory> {
        val review = Review(reviewId, userId, placeId, content!!, attachedPhotoIds!!, NORMAL)
        val reviewPointDetail = ReviewPointDetail.of(review, isFirstReview)
        val reviewPointDetailHistory = ReviewPointDetailHistory.of(userId, reviewPointDetail)
        return Triple(review, reviewPointDetail, reviewPointDetailHistory)
    }

    fun isValid(): Boolean {
//        check abuse, check empty content&attachedPhotoIds
//        check mandatory fields reviewId,userId,placeId
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EventLog

        if (type != other.type) return false
        if (action != other.action) return false
        if (reviewId != other.reviewId) return false
        if (content != other.content) return false
        if (attachedPhotoIds != null) {
            if (other.attachedPhotoIds == null) return false
            if (!attachedPhotoIds.contentEquals(other.attachedPhotoIds)) return false
        } else if (other.attachedPhotoIds != null) return false
        if (userId != other.userId) return false
        if (placeId != other.placeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + action.hashCode()
        result = 31 * result + reviewId.hashCode()
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (attachedPhotoIds?.contentHashCode() ?: 0)
        result = 31 * result + userId.hashCode()
        result = 31 * result + placeId.hashCode()
        return result
    }
}
