plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.0"
    defaultConfig {
        applicationId = "seki.com.re.hatenarssreader"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"http://b.hatena.ne.jp/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            val proFiles = File("app/proguard").list()
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro", *proFiles)
        }
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    // for coil
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Androidx.appcompat)
    implementation(Dependencies.Androidx.coreKtx)
    implementation(Dependencies.Androidx.constraintLayout)
    implementation(Dependencies.Androidx.recyclerView)
    implementation(Dependencies.Material.material)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.adapterRxJava2)

    implementation(Dependencies.OkHttp.okHttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)

    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.compiler)
    implementation(Dependencies.Dagger.android)
    kapt(Dependencies.Dagger.androidProcessor)

    implementation(Dependencies.RxJava2.rxJava)
    implementation(Dependencies.RxJava2.android)

    implementation(Dependencies.Coil.coil)

    debugImplementation(Dependencies.Stetho.stetho)
    implementation(Dependencies.Stetho.okHttp3)

    testImplementation(Dependencies.JUnit.junit)
    androidTestImplementation(Dependencies.Androidx.testRunner)
    androidTestImplementation(Dependencies.Androidx.espresso)
}
