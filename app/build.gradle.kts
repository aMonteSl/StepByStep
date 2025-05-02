plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}


android {
    namespace = "com.example.stepbystep"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.stepbystep"
        minSdk = 24
        targetSdk = 35
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

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.ktx)

    implementation(libs.androidx.room.runtime)
    implementation(libs.play.services.location)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.play.services.maps)

    implementation(libs.play.services.maps)
    implementation(libs.android.maps.utils.v230)

    // Add Gson dependency directly
//    implementation(libs.gson)

    implementation(libs.mpandroidchart)

    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Añade esta línea con tus otras dependencias
    implementation(libs.androidx.lifecycle.livedata.ktx) // Usa la versión más reciente
}