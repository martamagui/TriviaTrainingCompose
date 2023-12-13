import org.gradle.api.JavaVersion

object ConfigurationData {
    private const val _compileSdk = 34
    private const val _minSdk = 24
    private const val _targetSdk = 33
    private const val _testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    private const val _consumerProguardFiles = "consumer-rules.pro"
    private const val _proguardFile = "proguard-android-optimize.txt"
    private const val _proguardRules = "proguard-rules.pro"
    private val _javaVersion = JavaVersion.VERSION_17
    private const val _jvmTarget = "17"
    private const val _propertiesFileName = "secrets.properties"

    object App {
        const val nameSpace = "com.mmag.triviatraining"
        const val applicationId = "com.mmag.triviatraining"
        const val versionCode = 1
        const val versionName = "1.0"

        val compileSdk get() = _compileSdk
        val minSdk get() = _minSdk
        val targetSdk get() = _targetSdk
        val testInstrumentationRunner get() = _testInstrumentationRunner
        val consumerProguardFiles get() = _consumerProguardFiles
        val proguardFile get() = _proguardFile
        val proguardRules get() = _proguardRules
        val javaVersion get() = _javaVersion
        val jvmTarget get() = _jvmTarget
        const val kotlinCompiler = "1.4.3"
        val excludes = "/META-INF/{AL2.0,LGPL2.1}"
        val propertiesFileName get() = _propertiesFileName
    }

    object Module {
        const val dataSourceNameSpace = "com.mmag.datasource"
        const val dataBaseNameSpace = "com.mmag.database"
        val compileSdk get() = _compileSdk
        val minSdk get() = _minSdk
        val testInstrumentationRunner get() = _testInstrumentationRunner
        val consumerProguardFiles get() = _consumerProguardFiles
        val proguardFile get() = _proguardFile
        val proguardRules get() = _proguardRules
        val javaVersion get() = _javaVersion
        val jvmTarget get() = _jvmTarget
    }
}