import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "com.immanuelqrw.gitbase"
version = "0.0.1-Pre-Alpha"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.12.1")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.12.1")
    implementation("com.fasterxml.jackson.core", "jackson-annotations", "2.12.1")
    implementation("com.fasterxml.jackson.core", "jackson-core", "2.12.1")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.1")
    implementation("org.kohsuke", "github-api", "1.122")
    implementation("io.github.microutils", "kotlin-logging-jvm", "2.0.4")
    implementation("org.slf4j", "slf4j-simple", "1.7.30")
    testImplementation("com.fasterxml.jackson.core", "jackson-annotations", "2.12.1")
    testImplementation("com.fasterxml.jackson.core", "jackson-core", "2.12.1")
    testImplementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.1")
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.0")
    testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.7.0")
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", "5.7.0")
    testImplementation("io.mockk", "mockk", "1.10.5")
    testImplementation("org.amshove.kluent", "kluent", "1.61")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "4.8"
}
