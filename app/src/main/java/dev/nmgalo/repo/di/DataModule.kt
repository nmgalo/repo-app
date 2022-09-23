package dev.nmgalo.repo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.repo.data.search.SearchRepositoryImpl
import dev.nmgalo.repo.domain.search.SearchRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository
}
