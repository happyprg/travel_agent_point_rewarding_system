package com.whitemedia.spring.webflux.handler.user

import java.sql.Timestamp

data class User(val user: User, val createdAt: Timestamp)