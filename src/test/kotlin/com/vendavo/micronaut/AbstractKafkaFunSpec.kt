package com.vendavo.micronaut

import io.kotest.core.spec.style.FunSpec
import io.micronaut.test.support.TestPropertyProvider
import mu.KotlinLogging
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName

abstract class AbstractKafkaFunSpec : TestPropertyProvider, FunSpec() {

    private val logger = KotlinLogging.logger { }

    companion object {
        private val kafkaContainer: KafkaContainer = KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:latest")
        )
            .withNetwork(null)
    }

    override fun getProperties(): MutableMap<String, String> {
        if (!kafkaContainer.isRunning) {
            logger.info { "Starting Kafka container" }
            kafkaContainer.start()
        }
        logger.info { "Kafka bootstrap servers: ${kafkaContainer.bootstrapServers}" }
        return mutableMapOf(
            "kafka.bootstrap.servers" to kafkaContainer.bootstrapServers
        )
    }
}
