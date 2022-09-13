/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("dflow4k.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":dflow4k"))
}

application {
    // Define the main class for the application.
    mainClass.set("dflow4k.app.AppKt")
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useTestNG()
    }
}