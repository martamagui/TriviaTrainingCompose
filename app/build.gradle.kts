plugins {
    id(Plugins.application)
    id(Plugins.kotlinAndroid)
    id(Plugins.gradleSecrets)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = ConfigurationData.App.nameSpace
    compileSdk = ConfigurationData.App.compileSdk
    defaultConfig {
        applicationId = ConfigurationData.App.applicationId
        minSdk = ConfigurationData.App.minSdk
        targetSdk = ConfigurationData.App.targetSdk
        versionCode = ConfigurationData.App.versionCode
        versionName = ConfigurationData.App.versionName

        testInstrumentationRunner = ConfigurationData.App.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ConfigurationData.App.proguardFile),
                ConfigurationData.App.proguardRules
            )
            isDebuggable = false
        }
        debug {
            proguardFiles(
                getDefaultProguardFile(ConfigurationData.App.proguardFile),
                ConfigurationData.App.proguardRules
            )
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = ConfigurationData.App.javaVersion
        targetCompatibility = ConfigurationData.App.javaVersion
    }
    kotlinOptions {
        jvmTarget = ConfigurationData.App.jvmTarget
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ConfigurationData.App.kotlinCompiler
    }
    packaging {
        resources {
            excludes += ConfigurationData.App.excludes
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":app:data")))
    implementation(project(mapOf("path" to ":app:domain")))
    implementation(project(mapOf("path" to ":app:utils")))
    addAndroidXImplementations()
    addHiltImplementation()
    addRoomImplementation()
    addRetrofitImplementation()
    addDataStoreImplementation()
    addWorkImplementation()
    addTestImplementations()

}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = ConfigurationData.App.propertiesFileName
}
