package com.vendavo.micronaut

import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
data class Tenant(
    val id: UUID,
    var alias: String,
)
