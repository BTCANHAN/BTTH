// Top-level Gradle build configuration for the entire project

plugins {
    // Android plugin
    id("com.android.application") version "8.5.0" apply false

    // Google services plugin (for Firebase)
    id("com.google.gms.google-services") version "4.4.2" apply false
}
