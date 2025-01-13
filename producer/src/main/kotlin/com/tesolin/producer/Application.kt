package com.tesolin.producer

import com.tesolin.producer.service.MessageProducer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProducerApplication(private val messageProducer: MessageProducer) : CommandLineRunner {
    override fun run(vararg args: String?) {
        messageProducer.produceMessages()
    }
}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}