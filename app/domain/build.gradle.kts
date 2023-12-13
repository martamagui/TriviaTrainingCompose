plugins {
    id(Plugins.android)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = ConfigurationData.Module.domainNameSpace
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
    addAndroidXLibraryImplementations()
    addTestLibraryImplementations()
}