
plugins {
    id("com.android.application")
}

android {
    namespace = "com.inventory.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.inventory.app"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation("androidx.webkit:webkit:1.12.1")
}
