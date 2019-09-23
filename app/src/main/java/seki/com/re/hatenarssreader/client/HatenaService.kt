package seki.com.re.hatenarssreader.client

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HatenaService {

    companion object {
        private const val HOTENTORY = "hotentry.rss"
        private const val HOTENTORY_CATEGORY = "hotentry/{category}.rss"
    }

    @GET(HOTENTORY)
    fun fetchHotEntry(): Single<Response<ResponseBody>>

    @GET(HOTENTORY_CATEGORY)
    fun fetchHotEntry(@Path("category") category: String): Single<Response<ResponseBody>>
}