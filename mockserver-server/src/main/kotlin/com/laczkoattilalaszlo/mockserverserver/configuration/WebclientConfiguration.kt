package com.laczkoattilalaszlo.mockserverserver.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebclientConfiguration(
    @Value("\${custom-properties.register-client.port}") private val registerClientServerPort: String
) {

    @Bean
    fun getWebClient(): WebClient {
        return WebClient
            .builder()
            .baseUrl("http://localhost:${registerClientServerPort}")
            .build()
    }

}