package com.whitemedia.spring.webflux.handler.review

import java.sql.Timestamp
import java.sql.Timestamp.from
import java.time.Instant.now

data class Review(
        val reviewId: String,
        val userId: String,
        val placeId: String,
        val content: String,
        val attachPhotoIds: Array<String>,
        val statusType: ReviewStatusType,
        val createdAt: Timestamp = from(now()),
        val modifiedAt: Timestamp = from(now())
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Review

        if (reviewId != other.reviewId) return false
        if (userId != other.userId) return false
        if (placeId != other.placeId) return false
        if (content != other.content) return false
        if (!attachPhotoIds.contentEquals(other.attachPhotoIds)) return false
        if (statusType != other.statusType) return false
        if (createdAt != other.createdAt) return false
        if (modifiedAt != other.modifiedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = reviewId.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + placeId.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + attachPhotoIds.contentHashCode()
        result = 31 * result + statusType.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + modifiedAt.hashCode()
        return result
    }
}