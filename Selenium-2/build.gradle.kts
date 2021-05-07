plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("Selenium.AppKt")
}

dependencies {
    implementation ("org.seleniumhq.selenium:selenium-java:3.141.59")
}

val run by tasks.getting(JavaExec::class) {
    standardInput = System.`in`
}
tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}