plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.allopen)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.micronaut.application)
    alias(libs.plugins.micronaut.aot)
    alias(libs.plugins.shadow)
}

version = "0.1"
group = "com.vendavo.micronaut"

repositories {
    mavenCentral()
}

dependencies {
    kaptTest("io.micronaut:micronaut-inject-java")

    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.logging)

    testImplementation(libs.testcontainers.kafka)

    runtimeOnly(libs.logback)
    runtimeOnly("org.yaml:snakeyaml")
}

application {
    mainClass.set("com.vendavo.micronaut.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

micronaut {
    version(libs.versions.micronaut.asProvider().get())
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("com.vendavo.micronaut.*")
    }
}
