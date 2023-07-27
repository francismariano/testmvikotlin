//@file:Suppress("OPT_IN_IS_NOT_ENABLED")

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    id("io.gitlab.arturbosch.detekt")
    id("org.jmailen.kotlinter")
    id("dev.icerock.mobile.multiplatform-resources")
}

version = "1.0-SNAPSHOT"

kotlin {
    android()

//    jvm("desktop")

    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared code for the sample"
        homepage = "https://github.com/JetBrains/compose-jb"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
                api("com.arkivanov.mvikotlin:mvikotlin:3.2.1")
                api("com.arkivanov.mvikotlin:mvikotlin-main:3.2.1")
                api("com.arkivanov.mvikotlin:mvikotlin-logging:3.2.1")
                implementation("com.arkivanov.mvikotlin:rx:3.2.1")
                implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:3.2.1")
                api("com.arkivanov.decompose:decompose:2.0.1-compose-experimental")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:2.0.1-compose-experimental")
                implementation("co.touchlab:kermit:2.0.0-RC4")
                implementation("dev.icerock.moko:resources-compose:0.23.0")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
            }
        }
        val iosMain by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
//        val desktopMain by getting {
//            dependencies {
//                implementation(compose.desktop.common)
//            }
//        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library" // required
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir("src/androidMain/res")
    sourceSets["main"].resources.srcDir("src/commonMain/resources")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    namespace = "test.todoapp.lite.common"
}
