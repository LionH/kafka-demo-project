package com.tesolin.producer.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class MessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${kafka.topic}") private val topic: String,
    @Value("\${kafka.producer.message-count}") private val messageCount: Int,
    @Value("\${kafka.producer.delay-ms}") private val delayMs: Long
) {

    fun produceMessages() {
        repeat(messageCount) {
            val message = "Random message ${Random.nextInt()}"
            kafkaTemplate.send(topic, message)
            println("Sent: $message")
            Thread.sleep(delayMs)
        }
    }
}