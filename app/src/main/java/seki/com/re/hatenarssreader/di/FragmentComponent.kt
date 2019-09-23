package seki.com.re.hatenarssreader.di

import dagger.BindsInstance
import dagger.Subcomponent
import seki.com.re.hatenarssreader.infra.Category
import seki.com.re.hatenarssreader.presenter.articlelist.ArticleListBaseFragment

@FragmentScope
@Subcomponent(modules = [
    ViewModelModule::class
])
interface FragmentComponent {

    fun inject(fragment: ArticleListBaseFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance fun setCategory(category: Category): Builder
        fun build(): FragmentComponent
    }
}