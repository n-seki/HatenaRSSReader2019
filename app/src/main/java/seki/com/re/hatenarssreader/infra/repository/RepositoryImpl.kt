package seki.com.re.hatenarssreader.infra.repository

import io.reactivex.Single
import seki.com.re.hatenarssreader.client.HatenaRssXmlParser
import seki.com.re.hatenarssreader.client.HatenaService
import seki.com.re.hatenarssreader.data.Article
import seki.com.re.hatenarssreader.infra.Category
import seki.com.re.hatenarssreader.infra.Repository
import seki.com.re.hatenarssreader.infra.cache.RssCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RepositoryImpl @Inject constructor(
    private val service: HatenaService,
    private val cache: RssCache
) : Repository {

    override fun dirty(category: Category) {
        cache.dirty(category)
    }

    override fun fetchHotEntry(category: Category): Single<List<Article>> {
        if (!cache.has(category)) {
            return fetch(category)
        }
        return Single.just(cache.get(category))
    }

    private fun fetch(category: Category): Single<List<Article>> {
        val single = if (category == Category.ALL) {
            service.fetchHotEntry()
        } else {
            service.fetchHotEntry(category.value)
        }

        return single
            .map { HatenaRssXmlParser.parse(it.body()?.charStream()) }
            .doOnSuccess { cache.update(category, it) }
    }
}