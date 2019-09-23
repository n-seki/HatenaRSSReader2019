package seki.com.re.hatenarssreader.presenter.articledetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import seki.com.re.hatenarssreader.R

class ArticleDetailActivity : AppCompatActivity() {

    companion object {
        private const val URL = "url"
        private const val TITLE = "title"

        fun newIntent(context: Context, articleUrl: String, title: String): Intent {
            return Intent(context, ArticleDetailActivity::class.java).apply {
                putExtra(URL, articleUrl)
                putExtra(TITLE, title)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            title = intent.getStringExtra(TITLE)
            setDisplayHomeAsUpEnabled(true)
        }

        web_view.apply {
            webViewClient = WebViewClient()
            loadUrl(intent.getStringExtra(URL))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
            return
        }
        super.onBackPressed()
    }
}