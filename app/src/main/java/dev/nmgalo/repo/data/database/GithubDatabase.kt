package dev.nmgalo.repo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepoEntity::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
