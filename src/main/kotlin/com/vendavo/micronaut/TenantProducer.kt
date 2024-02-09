package com.vendavo.micronaut

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.messaging.annotation.MessageBody

const val TOPIC_NAME = "tenant"

@Suppress("kotlin:S6517")
@KafkaClient
interface TenantProducer {

    @Topic(TOPIC_NAME)
    fun produceTenant(@KafkaKey messageKey: String, @MessageBody tenant: Tenant)
}
