import Utils.addPlatform
import Utils.androidTestImplementation
import Utils.debugImplementation
import Utils.implementation
import Utils.kapt
import Utils.testImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addAndroidXImplementations() {
    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.runtimeKtx)
    implementation(Libs.Compose.composeActivity)
    addPlatform(Libs.Compose.composePlatform)
    implementation(Libs.Compose.composeUi)
    implementation(Libs.Compose.composeGraphics)
    implementation(Libs.Compose.composeToolingPreview)
    implementation(Libs.Compose.composeMaterial3)
    implementation(Libs.Compose.composeNavigation)
    //LifeCycle
    implementation(Libs.AndroidX.viewModelCompose)
    implementation(Libs.AndroidX.viewModelKtx)
    implementation(Libs.AndroidX.composeRuntime)
}

fun DependencyHandler.addRetrofitImplementation() {
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.gson)
    implementation(Libs.Retrofit.interceptor)
}

fun DependencyHandler.addHiltImplementation() {
    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.hiltCompiler)
    implementation(Libs.Hilt.composeNavigation)
}

fun DependencyHandler.addRoomImplementation() {
    implementation(Libs.Room.roomRuntime)
    kapt(Libs.Room.roomCompiler)
    implementation(Libs.Room.roomKtx)
}

fun DependencyHandler.addWorkImplementation() {
    implementation(Libs.Work.workRuntime)
    implementation(Libs.Hilt.hiltCommon)
    implementation(Libs.Hilt.hiltWork)
}

fun DependencyHandler.addDataStoreImplementation() {
    implementation(Libs.dataStore)
}

fun DependencyHandler.addTestImplementations() {
    testImplementation(Libs.Test.junit4)
    androidTestImplementation(Libs.Test.testExtJunit)
    androidTestImplementation(Libs.Test.espressoCore)
    androidTestImplementation(platform(Libs.Compose.composePlatform))
    androidTestImplementation(Libs.Compose.Debug.composeUiTestJunit4)
    debugImplementation(Libs.Compose.Debug.composeUiTooling)
    debugImplementation(Libs.Compose.Debug.composeUiTestManifest)
}

fun DependencyHandler.addAndroidXLibraryImplementations() {
    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.appCompat)
}

fun DependencyHandler.addTestLibraryImplementations() {
    testImplementation(Libs.Test.junit4)
    androidTestImplementation(Libs.Test.testExtJunit)
    androidTestImplementation(Libs.Test.espressoCore)
}