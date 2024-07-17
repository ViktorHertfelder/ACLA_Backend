package com.hertfelder.laptimerbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LaptimerBackendApplication

fun main(args: Array<String>) {
    runApplication<LaptimerBackendApplication>(*args)
}
