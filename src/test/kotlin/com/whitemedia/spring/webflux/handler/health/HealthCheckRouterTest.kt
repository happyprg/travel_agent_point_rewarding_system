package com.whitemedia.spring.webflux.handler.health

import com.whitemedia.spring.webflux.MainApplicationTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [MainApplicationTests::class])
class HealthCheckRouterTest(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert health check return ok`() {
        val entity = restTemplate.getForEntity<String>("/health/check")
        assertThat(entity.body).isEqualTo("ok")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }
}
