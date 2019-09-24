package seki.com.re.hatenarssreader.mapper

import android.content.Context
import okhttp3.ResponseBody
import retrofit2.Response
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.client.HatenaRssXmlParser
import seki.com.re.hatenarssreader.data.Article
import seki.com.re.hatenarssreader.data.Error
import seki.com.re.hatenarssreader.data.Result
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleResultMapper @Inject constructor(
    private val context: Context
) {
    fun map(response: Response<ResponseBody>): Result<List<Article>> {
        return if (response.isSuccessful) {
            val articles = HatenaRssXmlParser.parse(response.body()?.charStream())
            Result.Success(articles)
        } else {
            map(response.code())
        }
    }

    fun map(throwable: Throwable): Result<List<Article>> {
        val error = Error(
            title = context.getString(R.string.error_anything),
            message = context.getString(R.string.message_try_again)
        )
        return Result.Failure(error)
    }

    private fun map(errorCode: Int): Result<List<Article>> {
        val title = when (errorCode) {
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

        val error = Error(
            title = title,
            message = context.getString(R.string.message_try_again)
        )
        return Result.Failure(error)
    }
}