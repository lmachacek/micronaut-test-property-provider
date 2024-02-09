package com.vendavo.micronaut

import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject
import org.testcontainers.shaded.org.awaitility.Awaitility
import java.util.*
import java.util.concurrent.TimeUnit

@MicronautTest(startApplication = false)
class TestPropertyProviderAnnotationTest : AbstractKafkaFunSpec() {
    @Inject
    private lateinit var tenantProducer: TenantProducer

    @Inject
    private lateinit var tenantConsumer: TenantConsumer

    init {
        test("Tenant is produced into Kafka topic") {
            val tenant = Tenant(UUID.randomUUID(), "some-tenant")
            tenantProducer.produceTenant(tenant.id.toString(), tenant)
            Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .until { tenantConsumer.consumed?.id == tenant.id }
        }
    }
}
