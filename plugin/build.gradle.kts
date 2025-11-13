import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.3.0-Beta2"
    id("com.gradleup.shadow") version "8.3.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "me.ochryss.servidorteste"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(project(":common"))
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

val copyJar by tasks.registering(Copy::class) {
    dependsOn(tasks.withType<ShadowJar>())
    from(tasks.shadowJar.get().archiveFile)
    into("../build/")
    rename { "serversdk.jar" }
}