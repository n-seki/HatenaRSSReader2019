package seki.com.re.hatenarssreader.presenter.articlelist

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import seki.com.re.hatenarssreader.R
import seki.com.re.hatenarssreader.infra.Category

class HatenaArticleFragmentPageAdapter(
    fragmentManager: FragmentManager,
    context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // order for display
    private val page = listOf(
        Category.ALL to context.getString(R.string.category_all),
        Category.ECONOMICS to context.getString(R.string.category_economics),
        Category.LIFE to context.getString(R.string.category_life),
        Category.SOCIAL to context.getString(R.string.category_social)
    )

    override fun getItem(position: Int): Fragment {
        return when (page[position].first) {
            Category.ALL -> CategoryAllFragment()
            Category.ECONOMICS -> CategoryEconomicsFragment()
            Category.LIFE -> CategoryLifeFragment()
            Category.SOCIAL -> CategorySocialFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return page[position].second
    }

    override fun getCount(): Int = page.size
}