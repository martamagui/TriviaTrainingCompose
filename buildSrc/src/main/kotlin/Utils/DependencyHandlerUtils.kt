package Utils

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(lib: String): Dependency? = add("implementation", lib)

internal fun DependencyHandler.addPlatform(lib: String): Dependency? =
    add("implementation", platform(lib))

internal fun DependencyHandler.kapt(lib: String): Dependency? = add("kapt", lib)


internal fun DependencyHandler.testImplementation(lib: String): Dependency? =
    add("testImplementation", lib)


internal fun DependencyHandler.androidTestImplementation(lib: String): Dependency? =
    add("androidTestImplementation", lib)

internal fun DependencyHandler.androidTestImplementation(lib: Dependency): Dependency? =
    add("androidTestImplementation", lib)


fun DependencyHandler.debugImplementation(lib: String): Dependency? =
    add("debugImplementation", lib)

