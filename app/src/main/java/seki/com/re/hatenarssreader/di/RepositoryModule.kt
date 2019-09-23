package seki.com.re.hatenarssreader.di

import dagger.Binds
import dagger.Module
import seki.com.re.hatenarssreader.infra.Repository
import seki.com.re.hatenarssreader.infra.repository.RepositoryImpl

@Module
internal abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}