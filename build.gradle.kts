// build.gradle.kts (Project-level)

plugins {
    id("com.android.application") version "7.4.0" apply false
    // don’t want to run (apply) these plugins at the project level
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}
