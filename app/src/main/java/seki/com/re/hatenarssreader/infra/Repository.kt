package seki.com.re.hatenarssreader.infra

import io.reactivex.Single
import seki.com.re.hatenarssreader.data.Article
import seki.com.re.hatenarssreader.data.Result

interface Repository {
    fun dirty(category: Category)
    fun fetchHotEntry(category: Category): Single<Result<List<Article>>>
}