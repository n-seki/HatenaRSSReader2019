package seki.com.re.hatenarssreader.presenter.articlelist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("bind:image_url")
fun setArticleImage(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) {
        return
    }
    imageView.load(imageUrl) {
        crossfade(true)
    }
}

@BindingAdapter("bind:bookmark_visibility")
fun setVisibility(bookmark: ImageView, bookmarkCount: Int) {
    if (bookmarkCount < 0) {
        bookmark.visibility = View.GONE
    } else {
        bookmark.visibility = View.VISIBLE
    }
}

@BindingAdapter("bind:bookmark_count")
fun setBookmarkCount(textView: TextView, bookmarkCount: Int) {
    if (bookmarkCount < 0) {
        textView.visibility = View.GONE
        return
    }
    textView.text = bookmarkCount.toString()
    textView.visibility = View.VISIBLE
}