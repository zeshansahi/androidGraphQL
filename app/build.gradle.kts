import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.apollographql.apollo3").version("3.7.3")
}
apollo {
    generateKotlinModels.set(true)
    packageNamesFromFilePaths()
}

android {
    namespace = "com.example.graphql"
    compileSdk = 33

    defaultConfig {

        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
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
        jvmTarget = "17"
    }
    flavorDimensions += "version"
    productFlavors {
        create("Production") {
            android.buildFeatures.buildConfig = true
            applicationId = "com.example.graphql"
            versionCode = 1
            versionName = "VersionOne-$versionCode"
            resValue ("string", "app_name", "Graph QL")
            buildConfigField(
                "String",
                "baseUrl",
                "\"https://countries.trevorblades.com/\""
            )
        }
        create("Staging") {
            android.buildFeatures.buildConfig = true
            applicationId = "com.example.graphql.staging"
            versionCode = 1
            versionName = "VersionTwo-$versionCode"
            resValue ("string", "app_name", "Graph QL")
            buildConfigField(
                "String",
                "baseUrl",
                "\"https://countries.trevorblades.com/\""
            )

        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //imageview
    implementation("com.facebook.fresco:fresco:2.6.0")
    //
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")


    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation  ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation  ("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Kotlin and Kotlin Coroutines
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.activity:activity-ktx:1.7.2")
    // Architecture Components
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.slidingpanelayout:slidingpanelayout:1.2.0-rc01")
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

}
kapt {
    correctErrorTypes = true
}