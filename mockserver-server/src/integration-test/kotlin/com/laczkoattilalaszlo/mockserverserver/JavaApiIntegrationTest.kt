package com.laczkoattilalaszlo.mockserverserver

import com.fasterxml.jackson.databind.ObjectMapper
import com.laczkoattilalaszlo.mockserverserver.model.Person
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.timing.eventually
import io.kotest.assertions.until.FixedInterval
import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.integration.ClientAndServer
import org.mockserver.integration.ClientAndServer.startClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.mockserver.model.JsonBody
import org.mockserver.model.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.UUID
import kotlin.time.Duration.Companion.seconds


@SpringBootTest
@AutoConfigureWebTestClient(timeout = "PT10S")
class JavaApiIntegrationTest() {

    @Autowired private lateinit var webTestClient: WebTestClient
    @Autowired private lateinit var objectMapper: ObjectMapper
    @Autowired private lateinit var clientAndServer: ClientAndServer

    @BeforeEach
    fun startMockServer() {
        clientAndServer = startClientAndServer(8081)                             // The port for mock server should be the same as we used calling the client (8081)
    }

    @AfterEach
    fun stopMockServer() {
        clientAndServer.stop()
    }

    @Test
    fun `integration test`(): Unit = runBlocking {
        val id: String = UUID.randomUUID().toString()
        val person = Person(name = "test-name", age = 123)

        clientAndServer.`when`(
            HttpRequest
                .request()
                .withMethod("POST")
                .withPath("/register/register")
                .withContentType(MediaType.APPLICATION_JSON)
                .withBody(JsonBody(objectMapper.writeValueAsString( person )))
        ).respond(
            HttpResponse
                .response()
                .withContentType(MediaType.PLAIN_TEXT_UTF_8)
                .withBody("${id}")
                .withStatusCode(200)
        )

        assertSoftly {                                                                  // assertSoftly: With assertSoftly we are to be able to perform multiple assertions in one test.
            eventually(duration = 5.seconds, interval = FixedInterval(1.seconds)) {     // eventually: Periodically invokes the given lambda, ignoring specified exceptions, until the lambda passes.
                val responseBody: String = webTestClient
                    .post()
                    .uri("/person/register")
                    .bodyValue( person )
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(String::class.java)
                    .returnResult()
                    .responseBody!!

                responseBody shouldBe id                                                // 1st assertion

                clientAndServer.verify(                                                 // 2nd assertion, verify: Verifies that the request was sent to mock server.
                    HttpRequest
                        .request()
                        .withMethod("POST")
                        .withPath("/register/register")
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(JsonBody(objectMapper.writeValueAsString( person )))
                )
            }
        }
    }

}
