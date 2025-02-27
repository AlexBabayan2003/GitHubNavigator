plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.presentation_all_users"
    compileSdk = 35
buildFeatures.viewBinding = true
    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //glide
    implementation (libs.glide)
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    //modules
    implementation(projects.core)
    implementation(projects.featureAllUsers.domain)


    //navigation
    implementation(libs.androidx.navigation.fragment.ktx.v275)
    implementation(libs.androidx.navigation.ui.ktx.v275)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}