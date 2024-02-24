object Build {
    private const val androidBuildToolsVersion = "8.1.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    private const val hiltAndroidGradlePluginVersion = "2.48.1"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"


    private const val kpsPluginVersion = "1.9.0-1.0.13"
    const val kspPlugin = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$kpsPluginVersion"
}