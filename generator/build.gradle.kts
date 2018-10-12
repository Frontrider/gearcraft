import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.61"
    java
}

group = "hu.frontrider.gearcraft"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":annotations"))
   //compile(project(":mod"))
    compile(group = "com.esotericsoftware.yamlbeans", name = "yamlbeans", version = "1.13")
    compile("com.squareup:kotlinpoet:1.0.0-RC1")
    compile(kotlin("stdlib-jdk8"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}