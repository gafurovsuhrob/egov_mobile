plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "tj.ojsk.egov.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "tj.ojsk.egov.android"
        minSdk = 21
        targetSdk = compileSdk
        versionCode = 5
        versionName = properties["version"] as String
    }
    buildTypes {
        // debug
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.activity)
    implementation(libs.koin.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    coreLibraryDesugaring(libs.core.library.desugaring)
    implementation(libs.androidx.navigation.compose)
}
