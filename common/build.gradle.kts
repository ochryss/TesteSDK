plugins {
    kotlin("jvm") version "2.3.0-Beta2"
}

group = "me.ochryss.servidorteste.common"
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
}

tasks.test {
    useJUnitPlatform()
}