package seki.com.re.hatenarssreader.mapper

import android.content.Context
import android.util.Log
import retrofit2.HttpException
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.data.Error
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleErrorMapper @Inject constructor(
    private val context: Context
) {

    fun map(error: Throwable): Error {
        Log.d("aaaaa", error.toString())
        if (error !is HttpException) {
            return Error(
                title = context.getString(R.string.error_anything),
                message = context.getString(R.string.message_try_again)
            )
        }
        val title = when (error.code()) {
            in HttpURLConnection.HTTP_BAD_REQUEST..
                    HttpURLConnection.HTTP_UNSUPPORTED_TYPE -> {
                context.getString(R.string.network_error_request)
            }

            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> {
                context.getString(R.string.network_error_timeout)
            }

            else -> {
                context.getString(R.string.network_error_internal_error)
            }
        }

        return Error(
            title = title,
            message = context.getString(R.string.message_try_again),
            errorCode = error.code()
        )
    }
}