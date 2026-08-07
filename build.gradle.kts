plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "3.1.3"
}

group = "itcor"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("itcor.pomeedoro")
    mainClass.set("itcor.pomeedoro.Main")
}

javafx {
    version = "21.0.6"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.media")
}

jlink {
    options.set(listOf("--strip-debug", "--compress", "zip-6", "--no-header-files", "--no-man-pages"))
    launcher { name = "pomeedoro" }

    jpackage {
        imageName = "Pomeedoro"
        appVersion = "1.0"
        vendor = "Itcor"

        installerType = when {
            org.gradle.internal.os.OperatingSystem.current().isWindows -> "exe"
            org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "dmg"
            else -> (project.findProperty("pkg") as? String) ?: "deb"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}