import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.0"
}

group = "com.immanuelqrw.gitbase"
version = "0.0.1-Pre-Alpha"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.9.4.1")
    compile("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.9.0")
    compile("org.kohsuke", "github-api", "1.95")
    testCompile("junit", "junit", "4.12")
    testCompile("org.junit.jupiter", "junit-jupiter-api", "5.3.1")
    testCompile("org.junit.jupiter", "junit-jupiter-params", "5.3.1")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.3.1")
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