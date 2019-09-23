package seki.com.re.hatenarssreader.infra

import io.reactivex.Single
import seki.com.re.hatenarssreader.data.Article

interface Repository {
    fun dirty(category: Category)
    fun fetchHotEntry(category: Category): Single<List<Article>>
}