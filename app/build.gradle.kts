plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.githubnavigator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.githubnavigator"
        minSdk = 24
//        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx.v275)
    implementation(libs.androidx.navigation.ui.ktx.v275)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    //splashscreen
    implementation (libs.androidx.core.splashscreen)

    //data store
    implementation(libs.androidx.datastore.preferences)

    //glide
    implementation (libs.glide)
    kapt(libs.compiler)


    //modules
    implementation(projects.featureLogin.domain)
    implementation(projects.featureLogin.presentation)
    implementation(projects.featureLogin.data)
    implementation(projects.featureProfile.presentation)
    implementation(projects.featureProfile.domain)
    implementation(projects.featureProfile.data)
    implementation(projects.featureProfile.database)
    implementation(projects.featureUserRepos.presentation)
    implementation(projects.featureUserRepos.domain)
    implementation(projects.featureUserRepos.data)
    implementation(projects.featureUserRepos.database)
    implementation(projects.featureAllUsers.presentation)
    implementation(projects.featureAllUsers.domain)
    implementation(projects.featureAllUsers.data)
    implementation(projects.featureAllUsers.database)
    implementation(projects.core)
    implementation(projects.appDatabase)



    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}