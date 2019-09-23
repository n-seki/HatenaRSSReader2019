package seki.com.re.hatenarssreader.client

import android.content.Context
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import seki.com.re.hatenarssreader.BuildConfig
import seki.com.re.hatenarssreader.R
import javax.inject.Inject

class UserAgentInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    private object Key {
        const val USER_AGENT = "User-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headerBuilder = request.newBuilder()

        val userAgent =
            context.getString(R.string.app_name) + BuildConfig.VERSION_NAME + " Android/" + Build.VERSION.RELEASE
        headerBuilder
            .addHeader(Key.USER_AGENT, userAgent)

        return chain.proceed(request)
    }

}