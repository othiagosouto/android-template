plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "dev.thiagosouto.template"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.thiagosouto.template"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }


    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(platform(libs.android.compose.bom))
    implementation(libs.android.compose.foundation)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.preview)
    implementation(libs.android.hilt)
    implementation(libs.android.core)
    implementation(libs.android.material)

    implementation(libs.android.appcompat)
    testImplementation(libs.test.junit)



    kapt(libs.android.hilt.compiler)
    androidTestImplementation(libs.test.android.junit)
    androidTestImplementation(libs.test.android.espresso)
}