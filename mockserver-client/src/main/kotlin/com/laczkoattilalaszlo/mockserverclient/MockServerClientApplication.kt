package com.laczkoattilalaszlo.mockserverclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["com.laczkoattilalaszlo.mockserverclient"])
class MockServerApplication

fun main(args: Array<String>) {
	runApplication<MockServerApplication>(*args)
}
