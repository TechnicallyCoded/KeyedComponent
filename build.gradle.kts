import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.*

plugins {
    id("java")
    id("maven-publish")
}

group = "com.tcoded"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("net.kyori:adventure-api:4.25.0")
}

val targetJavaVersion = 21
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"

    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

tasks.named<Copy>("processResources") {
    val props = mapOf("version" to project.version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.compileJava {
    dependsOn(tasks.clean)
}

tasks.assemble {
    dependsOn(tasks.jar)
}

publishing {
    publications {
        create<MavenPublication>("mavenJavaLocal") {
            from(components["java"])
        }
    }
}

val enablePublishing: Boolean = project.findProperty("enableUploadPublish")?.toString()?.toBoolean() == true

if (enablePublishing) {
    publishing {
        repositories {
            maven {
                name = "reposilite"
                url = uri("https://repo.tcoded.com/releases")

                credentials {
                    username = project.findProperty("REPOSILITE_USER")?.toString()
                        ?: System.getenv("REPOSILITE_USER")
                                ?: error("REPOSILITE_USER property or environment variable is not set")
                    password = project.findProperty("REPOSILITE_PASS")?.toString()
                        ?: System.getenv("REPOSILITE_PASS")
                                ?: error("REPOSILITE_PASS property or environment variable is not set")
                }

                authentication {
                    register<BasicAuthentication>("basic")
                }
            }
        }
    }

    tasks.named("publishMavenJavaLocalPublicationToReposiliteRepository") {
        dependsOn(tasks.jar)
    }

}