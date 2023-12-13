plugins {
    id(Plugins.android)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    namespace = ConfigurationData.Module.dataNameSpace
    compileSdk = ConfigurationData.Module.compileSdk

    defaultConfig {
        minSdk = ConfigurationData.Module.minSdk

        testInstrumentationRunner = ConfigurationData.Module.testInstrumentationRunner
        consumerProguardFiles(ConfigurationData.Module.consumerProguardFiles)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ConfigurationData.Module.proguardFile),
                ConfigurationData.Module.proguardRules
            )
        }
    }
    compileOptions {
        sourceCompatibility = ConfigurationData.Module.javaVersion
        targetCompatibility = ConfigurationData.Module.javaVersion
    }
    kotlinOptions {
        jvmTarget = ConfigurationData.Module.jvmTarget
    }
}

dependencies {
    implementation(project(mapOf("path" to ":app:domain")))
    implementation(project(mapOf("path" to ":app:utils")))
    addAndroidXLibraryImplementations()
    addHiltImplementation()
    addRoomImplementation()
    addRetrofitImplementation()
    addTestLibraryImplementations()
}