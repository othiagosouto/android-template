import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

subprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=androidx.paging.ExperimentalPagingApi"
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}
