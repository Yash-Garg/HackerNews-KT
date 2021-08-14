import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "hack.er.news"
        minSdk = 24
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    lint {
        abortOnError = true
        checkReleaseBuilds = false
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

    kapt {
        correctErrorTypes = true
    }

    val keystoreConfigFile = rootProject.layout.projectDirectory.file("key.properties")
    if (keystoreConfigFile.asFile.exists()) {
        val contents = providers.fileContents(keystoreConfigFile).asText.forUseAtConfigurationTime()
        val keystoreProperties = Properties()
        keystoreProperties.load(contents.get().byteInputStream())
        signingConfigs {
            register("release") {
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
                storeFile = rootProject.file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
            }
        }
        buildTypes.all { signingConfig = signingConfigs.getByName("release") }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Dependencies.kotlin_version}")
    implementation("androidx.core:core-ktx:${Dependencies.core_ktx_version}")
    implementation("androidx.appcompat:appcompat:${Dependencies.appcompat_version}")
    implementation("androidx.activity:activity-ktx:${Dependencies.activity_version}")
    implementation("androidx.legacy:legacy-support-v4:${Dependencies.legacy_version}")

    // Design
    implementation("com.google.android.material:material:${Dependencies.material_version}")
    implementation("androidx.constraintlayout:constraintlayout:${Dependencies.constraint_layout_version}")

    // Retrofit with Moshi
    implementation("com.squareup.moshi:moshi-kotlin:${Dependencies.moshi_version}")
    implementation("com.squareup.retrofit2:converter-moshi:${Dependencies.retrofit_version}")
    implementation("dev.zacsweers.moshix:moshi-metadata-reflect:${Dependencies.reflect_version}")

    // Pagination
    implementation("androidx.paging:paging-runtime:${Dependencies.paging_version}")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:${Dependencies.shimmer_version}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${Dependencies.hilt_version}")
    kapt("com.google.dagger:hilt-compiler:${Dependencies.hilt_version}")

    // Lottie
    implementation("com.airbnb.android:lottie:${Dependencies.lottie_version}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Dependencies.lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.lifecycle_version}")
}
