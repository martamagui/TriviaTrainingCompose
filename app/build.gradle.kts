plugins {
    id(Plugins.App.application)
    id(Plugins.App.android)
    id(Plugins.App.gradleSecrets)
    kotlin(Plugins.App.kapt)
    id(Plugins.App.hilt)
}

android {
    namespace = "com.mmag.triviatraining"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.mmag.triviatraining"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
        }
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.runtimeKtx)
    implementation(Libs.Compose.composeActivity)
    implementation(platform(Libs.Compose.composePlatform))
    implementation(Libs.Compose.composeUi)
    implementation(Libs.Compose.composeGraphics)
    implementation(Libs.Compose.composeToolingPreview)
    implementation(Libs.Compose.composeMaterial3)


    //Dagger Hilt
    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.hiltCompiler)
    implementation(Libs.Hilt.composeNavigation)


    //LifeCycle
    implementation(Libs.AndroidX.viewModelCompose)
    implementation(Libs.AndroidX.viewModelKtx)
    implementation(Libs.AndroidX.composeRuntime)

    //Room
    implementation(Libs.Room.roomRuntime)
    kapt(Libs.Room.roomCompiler)
    implementation(Libs.Room.roomKtx)

    //Retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.gson)
    implementation(Libs.Retrofit.interceptor)

    //Compose navigation
    implementation(Libs.Compose.composeNavigation)

    //DataStore
    implementation(Libs.dataStore)

    //Work
    implementation(Libs.Work.workRuntime)
    implementation(Libs.Hilt.hiltCommon)
    implementation(Libs.Hilt.hiltWork)

    // Test
    testImplementation(Libs.Test.junit4)
    androidTestImplementation(Libs.Test.testExt)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(platform(Libs.Compose.composePlatform))
    androidTestImplementation(Libs.Compose.Debug.composeUiTestJunit4)
    debugImplementation(Libs.Compose.Debug.composeUiTooling)
    debugImplementation(Libs.Compose.Debug.composeUiTestManifest)

}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"
}