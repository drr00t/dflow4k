
plugins {
    id("dflow4k.kotlin-application-conventions")
}

group = "net.roadtoagility"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.konform:konform-jvm:0.4.0")
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useTestNG()
    }
}