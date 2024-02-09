package com.vendavo.micronaut

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class TenantConsumer {
    var consumed: Tenant? = null

    @Topic(TOPIC_NAME)
    fun consume(message: Tenant) {
        consumed = message
    }
}
