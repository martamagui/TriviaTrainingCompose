plugins {
    id(Plugins.java)
    id(Plugins.jvm)
}

java {
    sourceCompatibility = ConfigurationData.Module.javaVersion
    targetCompatibility = ConfigurationData.Module.javaVersion
}