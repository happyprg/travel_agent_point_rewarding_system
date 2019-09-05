package com.whitemedia.spring.webflux.handler.review

import org.springframework.stereotype.Repository

@Repository
class ReviewPointDetailHistoryRepository {
    fun save(reviewPointDetailHistory: ReviewPointDetailHistory): ReviewPointDetailHistory {
        return reviewPointDetailHistory
    }
}
