plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.omaroid.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.onboardingDomain))
}