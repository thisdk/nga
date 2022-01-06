package io.github.thisdk.bootstrap.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.thisdk.bootstrap.data.NewsRepository
import io.github.thisdk.bootstrap.data.NewsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {
    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}