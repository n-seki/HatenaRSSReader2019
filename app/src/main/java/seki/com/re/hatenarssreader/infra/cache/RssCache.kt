package seki.com.re.hatenarssreader.infra.cache

import seki.com.re.hatenarssreader.infra.Category
import seki.com.re.hatenarssreader.data.Article
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RssCache @Inject constructor() {

    private val cache: MutableMap<Category, List<Article>> = ConcurrentHashMap()

    fun has(category: Category) = cache.containsKey(category)

    fun get(category: Category) = cache[category]!!

    fun update(category: Category, articles: List<Article>) {
        cache[category] = articles
    }

    fun dirty(category: Category) {
        cache.remove(category)
    }
}