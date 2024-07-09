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
    val composeBom = platform(libs.android.compose.bom)
    implementation(composeBom)
    implementation(libs.android.compose.foundation)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.activity)
    implementation(libs.android.compose.preview)
    implementation(libs.android.lifecycle.viewmodel)
    implementation(libs.android.ktx.viewmodel)
    implementation(libs.android.hilt)
    implementation(libs.android.core)
    implementation(libs.android.material)

    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.okhttp)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.coroutines.android)

    implementation(libs.android.appcompat)

    kapt(libs.android.hilt.compiler)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.turbine)
    testImplementation(libs.test.core.android)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.webserver)

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.test.android.junit)
    androidTestImplementation(libs.test.android.espresso)
    androidTestImplementation(libs.test.hilt)
    androidTestImplementation(libs.test.webserver)
    androidTestImplementation(libs.test.core.android)
    androidTestImplementation(libs.test.android.compose)

    kaptAndroidTest(libs.android.hilt.compiler)
}
