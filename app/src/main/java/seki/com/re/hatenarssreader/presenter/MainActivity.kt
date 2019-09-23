package seki.com.re.hatenarssreader.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.data.Error
import seki.com.re.hatenarssreader.presenter.articlelist.ArticleListBaseFragment
import seki.com.re.hatenarssreader.presenter.articlelist.HatenaArticleFragmentPageAdapter

class MainActivity : AppCompatActivity(), Navigator, ArticleListBaseFragment.Listener {

    companion object {
        private const val ERROR_DIALOG_TAG = "error"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        viewPager.adapter = HatenaArticleFragmentPageAdapter(
            supportFragmentManager,
            this
        )
        viewPager.offscreenPageLimit = 1
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun showErrorDialog(error: Error) {
        supportFragmentManager.findFragmentByTag(ERROR_DIALOG_TAG)?.let {
            (it as ErrorDialogFragment).update(error)
            return@showErrorDialog
        }

        ErrorDialogFragment.newInstance(error)
            .show(supportFragmentManager, ERROR_DIALOG_TAG)
    }

    override fun goTo(intent: Intent) {
        startActivity(intent)
    }
}
