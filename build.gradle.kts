// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.application) version VersionConfig.application apply false
    id(Plugins.android) version VersionConfig.android apply false
    id(Plugins.hilt) version VersionConfig.hilt apply false
    id(Plugins.jvm) version VersionConfig.jvm apply false
}

buildscript {
    dependencies {
        classpath(BuildScript.gradleSecrets)
    }
}

