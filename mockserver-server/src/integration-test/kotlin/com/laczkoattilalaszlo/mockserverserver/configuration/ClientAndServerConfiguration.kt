package com.laczkoattilalaszlo.mockserverserver.configuration

import org.mockserver.integration.ClientAndServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientAndServerConfiguration {

    @Bean
    fun getClientAndSerer(): ClientAndServer {
        return ClientAndServer()
    }
}