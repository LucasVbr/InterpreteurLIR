plugins {
    java
    application
    id("com.diffplug.spotless") version "6.25.0"
    id("antlr")
}

repositories {
    mavenCentral()
}

spotless {
    java {
        googleJavaFormat()
    }
}

dependencies {
    implementation("org.antlr:antlr4-runtime:4.13.1")
    antlr("org.antlr:antlr4:4.13.1")

    implementation(libs.guava)

    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

sourceSets {
    main {
        java {
            srcDir("build/generated-src/antlr/main")
        }
    }
}

application {
    mainClass.set("iut_rodez.lir_lang.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named("build") {
}

tasks.named("run") {
}

tasks.named<AntlrTask>("generateGrammarSource") {
    arguments = listOf("-visitor", "-long-messages")
}
