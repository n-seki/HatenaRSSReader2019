package seki.com.re.hatenarssreader.presenter.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import seki.com.re.hatenarssreader.data.Article
import seki.com.re.hatenarssreader.data.Error
import seki.com.re.hatenarssreader.data.Result
import seki.com.re.hatenarssreader.infra.Category
import seki.com.re.hatenarssreader.infra.Repository
import seki.com.re.hatenarssreader.presenter.Event
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    private val category: Category,
    private val repository: Repository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _swipeRefreshing = MutableLiveData<Boolean>().apply { value = false }
    val swipeRefreshing: LiveData<Boolean> = _swipeRefreshing

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _error = MutableLiveData<Event<Error>>()
    val error: LiveData<Event<Error>> = _error


    fun startLoad() {
        fetch()
    }

    fun refresh() {
        repository.dirty(category)
        fetch()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun fetch() {
        _swipeRefreshing.postValue(true)
        repository
            .fetchHotEntry(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> onSuccessLoad(result) }
            .addTo(disposables)
    }

    private fun onSuccessLoad(result: Result<List<Article>>) {
        when (result) {
            is Result.Success -> {
                _articles.postValue(result.data)
                _swipeRefreshing.postValue(false)
            }
            is Result.Failure -> {
                _error.postValue(Event(result.error))
                _swipeRefreshing.postValue(false)
            }
        }
    }

    private fun Disposable.addTo(disposables: CompositeDisposable) {
        disposables.add(this)
    }
}