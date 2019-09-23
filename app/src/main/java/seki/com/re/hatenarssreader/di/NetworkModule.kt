package seki.com.re.hatenarssreader.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import seki.com.re.hatenarssreader.BuildConfig
import seki.com.re.hatenarssreader.client.HatenaService
import seki.com.re.hatenarssreader.client.UserAgentInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideHatanaService(
        okHttpClient: OkHttpClient
    ): HatenaService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(HatenaService::class.java)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkhttpForRetrofit(
        @Named("logging") loggingInterceptor: Interceptor,
        @Named("userAgent") userAgentInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(userAgentInterceptor)

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(StethoInterceptor())
        }
        return client.build()
    }

    @Singleton
    @Provides
    @Named("logging")
    @JvmStatic
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Singleton
    @Provides
    @Named("userAgent")
    @JvmStatic
    fun provideUserAgentInterceptor(userAgentInterceptor: UserAgentInterceptor): Interceptor {
        return userAgentInterceptor
    }
}