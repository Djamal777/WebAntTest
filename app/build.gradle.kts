plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.example.webanttes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.webanttes"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.unsplash.com/\"")
            buildConfigField("String", "ACCESS_KEY", "\"nOp15WQMHcgW4izrYPcqMgV07509t6SMS4WUSHeRC2s\"")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit2)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.paging)

    implementation(libs.swiperefreshlayout)

    implementation(libs.glide)
}