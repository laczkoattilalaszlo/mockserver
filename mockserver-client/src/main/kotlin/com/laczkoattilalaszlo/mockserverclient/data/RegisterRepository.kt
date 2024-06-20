package com.laczkoattilalaszlo.mockserverclient.data

import com.laczkoattilalaszlo.mockserverclient.model.Person
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RegisterRepository(
    private var inMemoryRegister: List<Person> = mutableListOf()
) {

    fun registerPerson(person: Person): String {
        val id: String = UUID.randomUUID().toString()
        inMemoryRegister += Person(id = id, name = person.name, age = person.age)
        return id
    }

    fun retrievePerson(id: String): Person {
        return inMemoryRegister.find { it.id == id } !!
    }
}