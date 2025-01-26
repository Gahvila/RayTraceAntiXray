plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-SNAPSHOT"
}

group = "com.vanillage.raytraceantixray"
version = "1.17.0"
description = "RayTraceAntiXray"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    maven { url = uri("https://repo.papermc.io/repository/maven-snapshots/") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/groups/public/") }
    maven { url = uri("https://libraries.minecraft.net/") }
    //maven { url = uri("https://repo.dmulloy2.net/repository/public/") }
    maven { url = uri("https://repo.maven.apache.org/maven2/") }
}

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")

    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    //compileOnly("io.papermc.paper:paper-mojangapi:1.21-R0.1-SNAPSHOT")
    compileOnly("com.mojang:datafixerupper:5.0.28")
    //compileOnly("com.comphenix.protocol:ProtocolLib:5.0.0")
}

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))

    // Upstream branch was a multi-module maven project
    sourceSets.getByName("main") {
        java.srcDir("RayTraceAntiXray/src/main/java")
        resources.srcDir("RayTraceAntiXray/src/main/resources")
    }
}

tasks {
    // Configure reobfJar to run when invoking the build task
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(21)
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
