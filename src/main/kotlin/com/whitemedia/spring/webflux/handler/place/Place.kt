package com.whitemedia.spring.webflux.handler.place

import java.sql.Timestamp

data class Place(val placeId: String, val createdAt: Timestamp)