package br.com.server.firebase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class FirebaseApplication

fun main() {
    runApplication<FirebaseApplication>()
}