plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.data.login"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
//        targetSdk = 35

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

    //room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    //modules
    implementation(projects.core)
    implementation(projects.featureUserRepos.data)
    implementation(projects.featureAllUsers.data)
    implementation(projects.featureProfile.domain)
    implementation(projects.featureLogin.domain)
    implementation(projects.featureUserRepos.database)
    implementation(projects.appDatabase)
    implementation(projects.featureProfile.database)
    implementation(projects.featureAllUsers.database)


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}