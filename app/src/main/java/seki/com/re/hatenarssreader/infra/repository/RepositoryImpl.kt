package seki.com.re.hatenarssreader.infra.repository

import io.reactivex.Single
import seki.com.re.hatenarssreader.client.HatenaService
import seki.com.re.hatenarssreader.data.Article
import seki.com.re.hatenarssreader.data.Result
import seki.com.re.hatenarssreader.infra.Category
import seki.com.re.hatenarssreader.infra.Repository
import seki.com.re.hatenarssreader.infra.cache.RssCache
import seki.com.re.hatenarssreader.mapper.ArticleResultMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RepositoryImpl @Inject constructor(
    private val service: HatenaService,
    private val cache: RssCache,
    private val mapper: ArticleResultMapper
) : Repository {

    override fun dirty(category: Category) {
        cache.dirty(category)
    }

    override fun fetchHotEntry(category: Category): Single<Result<List<Article>>> {
        if (!cache.has(category)) {
            return fetch(category)
        }
        return Single.just(Result.Success(cache.get(category)))
    }

    private fun fetch(category: Category): Single<Result<List<Article>>> {
        val single = if (category == Category.ALL) {
            service.fetchHotEntry()
        } else {
            service.fetchHotEntry(category.value)
        }

        return single
            .map { mapper.map(it) }
            .doOnSuccess {
                if (it is Result.Success) {
                    cache.update(category, it.data)
                }
            }
            .onErrorReturn {
                mapper.map(it)
            }
    }
}