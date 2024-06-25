package com.laczkoattilalaszlo.mockserverserver.service

import com.laczkoattilalaszlo.mockserverserver.client.RegisterClient
import com.laczkoattilalaszlo.mockserverserver.model.Person
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PersonService(
    private val registerClient: RegisterClient
) {

    fun registerPerson(person: Person): Mono<String> {
        return registerClient.registerPerson(person = person)
    }

    fun retrievePerson(id: String): Mono<Person> {
        return registerClient.retrievePerson(id = id)
    }
}