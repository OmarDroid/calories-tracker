// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath (Build.kspPlugin)
        //classpath(Build.kspPlugin)
    }
}
/*plugins {
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}*/
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}