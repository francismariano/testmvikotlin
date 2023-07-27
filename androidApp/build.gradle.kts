plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("io.gitlab.arturbosch.detekt")
    id("org.jmailen.kotlinter")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.material)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                // para implementar o gif//
                implementation("io.coil-kt:coil-compose:2.3.0")
                implementation("io.coil-kt:coil-gif:2.3.0")
            }
        }
    }
}

dependencies {
    implementation(compose.preview)
    debugImplementation(compose.uiTooling)
}

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
        }
    }
    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin/")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    namespace = "me.com.francis"
}