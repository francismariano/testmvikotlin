import java.util.*

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("com.github.ben-manes.versions")
    id("io.gitlab.arturbosch.detekt") apply false
    id("org.jmailen.kotlinter") apply false
    id("dev.icerock.mobile.multiplatform-resources") apply false
    id("com.google.gms.google-services") apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
        version.uppercase(Locale.getDefault())
            .contains(it)
    }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}
