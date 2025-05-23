plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)

}

android {
    namespace = "com.undef.manoslocalesproyecto"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.undef.manoslocalesproyecto"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.activity:activity-compose:1.11.0-rc01")
    implementation("androidx.compose.ui:ui:1.9.0-alpha01")
    implementation("androidx.compose.material3:material3:1.4.0-alpha12")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0-rc01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0-alpha07")
    implementation ("androidx.activity:activity-ktx:1.8.0")  // o la última estable
    implementation ("androidx.core:core-ktx:1.12.0")          // o versión reciente
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.compose.material:material-icons-extended")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.compose.ui:ui:1.6.0") // o tu versión
    implementation ("androidx.activity:activity-compose:1.8.0")
    implementation("com.google.accompanist:accompanist-flowlayout:0.30.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.compose.material3:material3:1.3.2")

    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")


    implementation("androidx.datastore:datastore-preferences:1.0.0")


}