package seki.com.re.hatenarssreader.presenter.articlelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_arcitle_list.*
import seki.com.re.hatenarssreader.App
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.data.Article
import seki.com.re.hatenarssreader.data.Error
import seki.com.re.hatenarssreader.infra.Category
import seki.com.re.hatenarssreader.presenter.ErrorDialogFragment
import seki.com.re.hatenarssreader.presenter.Navigator
import seki.com.re.hatenarssreader.presenter.articledetail.ArticleDetailActivity
import javax.inject.Inject

abstract class ArticleListBaseFragment : Fragment(), OnClickListener {

    abstract val category: Category

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(ArticleListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).applicationComponent.fragmentComponentBuilder()
            .setCategory(category)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_arcitle_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }

        recycler_view.apply {
            adapter = ArticleListAdapter(this@ArticleListBaseFragment as OnClickListener)
        }

        viewModel.swipeRefreshing.observe(viewLifecycleOwner, Observer {
            swipe_refresh.isRefreshing = it
        })

        viewModel.articles.observe(viewLifecycleOwner, Observer {
            (recycler_view.adapter as ArticleListAdapter).submitList(it)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error.getContentIfNotHandled()?.let {
                if (context is Listener) {
                    (context as Listener).showErrorDialog(it)
                }
            }
        })

        viewModel.startLoad()
    }

    override fun onClick(article: Article) {
        val intent = ArticleDetailActivity.newIntent(
            context = context!!,
            title = article.title,
            articleUrl = article.link
        )
        if (context is Navigator) {
            (context as Navigator).goTo(intent)
        }
    }

    interface Listener {
        fun showErrorDialog(error: Error)
    }
}