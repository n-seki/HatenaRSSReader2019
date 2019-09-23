package seki.com.re.hatenarssreader.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    FragmentModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
abstract class ApplicationComponent {

    abstract fun fragmentComponentBuilder(): FragmentComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }
}