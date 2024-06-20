package com.laczkoattilalaszlo.mockserverserver.client

import com.laczkoattilalaszlo.mockserverserver.model.Person
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class RegisterClient(
    private val webClient: WebClient
) {

    fun registerPerson(person: Person): Mono<String> {
        return webClient
            .post()
            .uri("/register/register")
            .bodyValue(person)
            .exchangeToMono { clientResponse -> clientResponse.bodyToMono(String::class.java) }
    }

    fun retrievePerson(id: String): Mono<Person> {
        return webClient
            .get()
            .uri("/register/retrieve/${id}")
            .exchangeToMono { clientResponse -> clientResponse.bodyToMono(Person::class.java) }
    }
}