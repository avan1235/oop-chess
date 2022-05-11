import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("com.github.johnrengelman.shadow") version "7.1.2"
  java
}

group = "pl.edu.mimuw"
version = "2022"

repositories {
  mavenCentral()
}

tasks {
  named<ShadowJar>("shadowJar") {
    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "pl.edu.mimuw.Main"))
    }
  }
}
