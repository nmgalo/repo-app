package dev.nmgalo.repo.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.repo.data.database.GithubDatabase
import dev.nmgalo.repo.data.database.RepoDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesGithubDatabase(@ApplicationContext context: Context): GithubDatabase =
        Room.databaseBuilder(context, GithubDatabase::class.java, "github-database").build()


    @Provides
    fun providesRepoDao(
        database: GithubDatabase
    ): RepoDao = database.repoDao()
}
