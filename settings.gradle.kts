pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String
        val detektVersion = extra["detekt.version"] as String
        val pluginCheckVersion = extra["plugin.check.version"] as String
        val kotlinterVersion = extra["kotlinter.version"] as String
        val mokoResourcesVersion = extra["mokoresources.version"] as String
        val firebaseVersion = extra["firebase.version"] as String
        val crashlyticsVersion = extra["crashlytics.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        id("com.android.base").version(agpVersion)
        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
        id("io.gitlab.arturbosch.detekt").version(detektVersion)
        id("com.github.ben-manes.versions").version(pluginCheckVersion)
        id("org.jmailen.kotlinter").version(kotlinterVersion)
        id("dev.icerock.mobile.multiplatform-resources").version(mokoResourcesVersion)
        id("com.google.gms.google-services").version(firebaseVersion)
        id("com.google.firebase.crashlytics").version(crashlyticsVersion)
    }
}

rootProject.name = "Test"

include(":androidApp")
include(":shared")
//include(":desktopApp")
