package com.whitemedia.spring.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class MainApplicationTests

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args)
}
