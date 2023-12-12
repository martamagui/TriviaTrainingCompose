object Libs {
    object AndroidX {
        val core by lazy { "androidx.core:core-ktx:1.9.0" }
        val runtimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2" }
        val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${VersionConfiguration.lifecycleVersion}" }
        val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${VersionConfiguration.lifecycleVersion}" }
        val composeRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${VersionConfiguration.lifecycleVersion}" }
    }

    object Retrofit {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${VersionConfiguration.retrofit}" }
        val gson by lazy { "com.squareup.retrofit2:converter-gson:${VersionConfiguration.retrofit}" }
        val interceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${VersionConfiguration.okHttp}" }
    }

    object Hilt {
        val hilt by lazy { "com.google.dagger:hilt-android:${VersionConfiguration.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${VersionConfiguration.hilt}" }
        val composeNavigation by lazy { "androidx.hilt:hilt-navigation-compose:${VersionConfiguration.hiltNavigation}" }
        val hiltCommon by lazy { "androidx.hilt:hilt-common:1.1.0" }
        val hiltWork by lazy { "androidx.hilt:hilt-work:1.1.0" }
    }

    object Room {
        val roomRuntime by lazy { "androidx.room:room-runtime:${VersionConfiguration.roomVersion}" }
        val roomCompiler by lazy { "androidx.room:room-compiler:${VersionConfiguration.roomVersion}" }
        val roomKtx by lazy { "androidx.room:room-ktx:${VersionConfiguration.roomVersion}" }
    }

    object Compose {
        val composeActivity by lazy { "androidx.activity:activity-compose:1.8.0" }
        val composePlatform by lazy { "androidx.compose:compose-bom:2023.03.00" }
        val composeUi by lazy { "androidx.compose.ui:ui" }
        val composeGraphics by lazy { "androidx.compose.ui:ui-graphics" }
        val composeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
        val composeMaterial3 by lazy { "androidx.compose.material3:material3" }
        val composeNavigation by lazy { "androidx.navigation:navigation-compose:${VersionConfiguration.navVersion}" }

        object Debug {
            val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
            val composeUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest" }
            val composeUiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4" }
        }
    }

    object Work {
        val workRuntime by lazy { "androidx.work:work-runtime-ktx:2.8.1" }
    }

    object Test {
        val junit4 by lazy { "junit:junit:4.13.2" }
        val testExt by lazy { "androidx.test.ext:junit:1.1.5" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:3.5.1" }
    }

    val dataStore by lazy { "androidx.datastore:datastore-preferences:1.0.0" }
}