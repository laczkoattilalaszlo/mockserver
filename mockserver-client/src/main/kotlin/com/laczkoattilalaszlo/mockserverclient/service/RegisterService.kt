package com.laczkoattilalaszlo.mockserverclient.service

import com.laczkoattilalaszlo.mockserverclient.data.RegisterRepository
import com.laczkoattilalaszlo.mockserverclient.model.Person
import org.springframework.stereotype.Service

@Service
class RegisterService(
    private val registerRepository: RegisterRepository
) {

    fun registerPerson(person: Person): String {
        return registerRepository.registerPerson(person)
    }

    fun retrievePerson(id: String): Person {
        return registerRepository.retrievePerson(id)
    }
}