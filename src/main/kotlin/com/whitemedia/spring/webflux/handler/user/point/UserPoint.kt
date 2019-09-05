package com.whitemedia.spring.webflux.handler.user.point

import com.whitemedia.spring.webflux.handler.point.PointType

data class UserPoint(val userId: String, val pointType: PointType, val totalPoint: Long)
