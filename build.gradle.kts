import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.70"
}

group = "com.immanuelqrw.gitbase"
version = "0.0.1-Pre-Alpha"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.10.0")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.10.0")
    implementation("com.fasterxml.jackson.core", "jackson-annotations", "2.10.2")
    implementation("com.fasterxml.jackson.core", "jackson-core", "2.10.2")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.10.4")
    implementation("org.kohsuke", "github-api", "1.111")
    testImplementation("com.fasterxml.jackson.core", "jackson-annotations", "2.10.2")
    testImplementation("com.fasterxml.jackson.core", "jackson-core", "2.10.2")
    testImplementation("com.fasterxml.jackson.core", "jackson-databind", "2.10.4")
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.3.1")
    testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.3.1")
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", "5.3.1")
    testImplementation("io.mockk", "mockk", "1.8.13")
    testImplementation("org.amshove.kluent", "kluent", "1.42")
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
