package com.laczkoattilalaszlo.mockserverserver.controller

import com.laczkoattilalaszlo.mockserverserver.model.Person
import com.laczkoattilalaszlo.mockserverserver.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/person")
class PersonController(
    private var personService: PersonService
) {

    @PostMapping("/register")
    fun registerPerson(@RequestBody person: Person): Mono<ResponseEntity<String>> {
        return personService.registerPerson(person = person).map { ResponseEntity(it, HttpStatus.OK) }
    }

    @GetMapping("/retrieve/{id}")
    fun retrievePerson(@PathVariable id: String): Mono<ResponseEntity<Person>> {
        return personService.retrievePerson(id = id).map { ResponseEntity(it, HttpStatus.OK) }
    }

}
