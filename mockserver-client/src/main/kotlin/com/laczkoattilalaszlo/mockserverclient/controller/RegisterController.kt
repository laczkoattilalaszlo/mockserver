package com.laczkoattilalaszlo.mockserverclient.controller

import com.laczkoattilalaszlo.mockserverclient.model.Person
import com.laczkoattilalaszlo.mockserverclient.service.RegisterService
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
@RequestMapping("/register")
class RegisterController(
    private val registerService: RegisterService
) {

    @PostMapping("/register")
    fun registerPerson(@RequestBody person: Person): Mono<ResponseEntity<String>> {
        val id: String = registerService.registerPerson(person = person)
        return Mono.just(ResponseEntity(id, HttpStatus.OK))
    }

    @GetMapping("/retrieve/{id}")
    fun retrievePerson(@PathVariable id: String): Mono<ResponseEntity<Person>> {
        val person: Person = registerService.retrievePerson(id = id)
        return Mono.just(ResponseEntity(person, HttpStatus.OK))
    }

}
