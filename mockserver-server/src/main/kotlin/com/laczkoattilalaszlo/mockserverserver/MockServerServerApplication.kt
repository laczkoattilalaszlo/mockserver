package com.laczkoattilalaszlo.mockserverserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["com.laczkoattilalaszlo.mockserverserver"])
class MockServerApplication

fun main(args: Array<String>) {
	runApplication<MockServerApplication>(*args)
}
