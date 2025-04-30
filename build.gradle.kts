//plugins {
//    //trick: for the same plugin versions in all sub-modules
//    alias(libs.plugins.androidApplication).apply(false)
//    alias(libs.plugins.androidLibrary).apply(false)
//    alias(libs.plugins.kotlinMultiplatform).apply(false)
//    alias(libs.plugins.composeCompiler).apply(false)
//    alias(libs.plugins.composeMultiplatform).apply(false)
//    alias(libs.plugins.kotlinxSerialization).apply(false)
//    alias(libs.plugins.kmpNativeCoroutines).apply(false)
//    alias(libs.plugins.sqlDelight) apply false
//    alias(libs.plugins.gradleVersionsPlugin) apply false
//    alias(libs.plugins.shadowPlugin) apply false
//    alias(libs.plugins.kotlinCocoapods) apply false
//}
//
////
////plugins {
////    alias(libs.plugins.androidApplication) apply false
////    alias(libs.plugins.androidLibrary) apply false
////    alias(libs.plugins.kotlinAndroid) apply false
////    alias(libs.plugins.kotlinMultiplatform) apply false
////    alias(libs.plugins.kotlinxSerialization) apply false
////    alias(libs.plugins.kmpNativeCoroutines) apply false
////    alias(libs.plugins.sqlDelight) apply false
////}
//
//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.nativeCocoapod) apply false
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.spotless)
    id("dev.iurysouza.modulegraph") version "0.10.1"
    alias(libs.plugins.compose.compiler) apply false
}

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "### Module Graph"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            licenseHeaderFile(
                rootProject.file("${project.rootDir}/spotless/copyright.kt"),
                "^(package|object|import|interface)",
            )
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("$buildDir/**/*.kts")
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "(^(?![\\/ ]\\*).*$)")
        }
        format("misc") {
            target("**/*.md", "**/.gitignore")
            trimTrailingWhitespace()
            indentWithTabs()
            endWithNewline()
        }
    }
}