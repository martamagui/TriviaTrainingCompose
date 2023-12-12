// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.application) version "8.1.1" apply false
    id(Plugins.android) version "1.8.10" apply false
    id(Plugins.hilt) version "2.44" apply false
    id(Plugins.jvm) version "1.8.10" apply false
}

buildscript {
    dependencies {
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

