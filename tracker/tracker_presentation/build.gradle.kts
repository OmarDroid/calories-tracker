plugins {
    `android-library`
    `kotlin-android`
}

apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = "com.omaroid.tracker_presentation"
    compileSdk = 34
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))
    implementation(Coil.coilCompose)
}