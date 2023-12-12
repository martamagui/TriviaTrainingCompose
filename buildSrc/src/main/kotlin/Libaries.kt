object Libs {
    object AndroidX {
        val core by lazy { "androidx.core:core-ktx:${VersionConfig.androidCore}" }
        val runtimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${VersionConfig.lifecycleVersion}" }
        val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${VersionConfig.lifecycleVersion}" }
        val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${VersionConfig.lifecycleVersion}" }
        val composeRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${VersionConfig.lifecycleVersion}" }
    }

    object Retrofit {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${VersionConfig.retrofit}" }
        val gson by lazy { "com.squareup.retrofit2:converter-gson:${VersionConfig.retrofit}" }
        val interceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${VersionConfig.okHttp}" }
    }

    object Hilt {
        val hilt by lazy { "com.google.dagger:hilt-android:${VersionConfig.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${VersionConfig.hilt}" }
        val composeNavigation by lazy { "androidx.hilt:hilt-navigation-compose:${VersionConfig.hiltNavigation}" }
        val hiltCommon by lazy { "androidx.hilt:hilt-common:${VersionConfig.hiltCommon}" }
        val hiltWork by lazy { "androidx.hilt:hilt-work:${VersionConfig.hiltWork}" }
    }

    object Room {
        val roomRuntime by lazy { "androidx.room:room-runtime:${VersionConfig.roomVersion}" }
        val roomCompiler by lazy { "androidx.room:room-compiler:${VersionConfig.roomVersion}" }
        val roomKtx by lazy { "androidx.room:room-ktx:${VersionConfig.roomVersion}" }
    }

    object Compose {
        val composeActivity by lazy { "androidx.activity:activity-compose:${VersionConfig.compose}" }
        val composePlatform by lazy { "androidx.compose:compose-bom:${VersionConfig.composeBom}" }
        val composeUi by lazy { "androidx.compose.ui:ui" }
        val composeGraphics by lazy { "androidx.compose.ui:ui-graphics" }
        val composeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
        val composeMaterial3 by lazy { "androidx.compose.material3:material3" }
        val composeNavigation by lazy { "androidx.navigation:navigation-compose:${VersionConfig.navVersion}" }

        object Debug {
            val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
            val composeUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest" }
            val composeUiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4" }
        }
    }

    object Work {
        val workRuntime by lazy { "androidx.work:work-runtime-ktx:${VersionConfig.work}" }
    }

    object Test {
        val junit4 by lazy { "junit:junit:${VersionConfig.jUnit4}" }
        val testExt by lazy { "androidx.test.ext:junit:${VersionConfig.jUnit}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${VersionConfig.espresso}" }
    }

    val dataStore by lazy { "androidx.datastore:datastore-preferences:${VersionConfig.dataStore}" }
}