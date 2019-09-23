object Dependencies {

    object Kotlin {
        const val version = "1.3.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Androidx {
        private const val version = "1.1.0"
        const val appcompat = "androidx.appcompat:appcompat:$version"
        const val coreKtx = "androidx.core:core-ktx:$version"

        private const val recyclerViewVersion = "1.0.0"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"

        private const val constrainLayoutVersion = "1.1.3"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constrainLayoutVersion"

        private const val testRunnerVersion = "1.2.0"
        const val testRunner = "androidx.test:runner:$testRunnerVersion"

        private const val espressoVersion = "3.2.0"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    }

    object Material {
        private const val version = "1.1.0-alpha07"
        const val material = "com.google.android.material:material:$version"
    }

    object Retrofit {
        private const val version = "2.6.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:$version"
    }

    object OkHttp {
        private const val version = "4.2.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Dagger {
        private const val version = "2.22.1"
        const val dagger = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }

    object RxJava2 {
        private const val version = "2.2.12"
        const val rxJava = "io.reactivex.rxjava2:rxjava:$version"

        private const val androidVersion = "2.1.1"
        const val android = "io.reactivex.rxjava2:rxandroid:$androidVersion"
    }

    object Coil {
        private const val version = "0.7.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object Stetho {
        private const val version = "1.5.1"
        const val stetho = "com.facebook.stetho:stetho:$version"
        const val okHttp3 = "com.facebook.stetho:stetho-okhttp3:$version"
    }

    object JUnit {
        private const val version = "4.12"
        const val junit = "junit:junit:$version"
    }
}