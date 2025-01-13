package com.tesolin.consumer.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageConsumer {

    @KafkaListener(topics = ["\${kafka.topic}"], groupId = "\${kafka.consumer.group-id}")
    fun listen(message: String) {
        println("Received: $message")
    }
}