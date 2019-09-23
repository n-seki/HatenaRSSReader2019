package seki.com.re.hatenarssreader.presenter.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.databinding.LayoutArticleBinding
import seki.com.re.hatenarssreader.data.Article

class ArticleListAdapter(
    private val articleClickListener: OnClickListener
) : ListAdapter<Article, ArticleViewHolder>(ArticleCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.binding.apply {
            article = getItem(position)
            clickListener = articleClickListener
        }
    }
}

object ArticleCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

interface OnClickListener {
    fun onClick(article: Article)
}

class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = LayoutArticleBinding.bind(view)!!
}